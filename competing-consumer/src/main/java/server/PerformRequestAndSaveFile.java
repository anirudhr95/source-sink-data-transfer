package server;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.linecorp.armeria.client.Clients;
import com.sink.queue.GetMessageFromQueueGrpc;
import com.sink.queue.RequestFromSink;
import com.sink.queue.ResponseFromQueueSource;

public class PerformRequestAndSaveFile implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(PerformRequestAndSaveFile.class);

    private GetMessageFromQueueGrpc.GetMessageFromQueueFutureStub sinkResponseAsyncStub;
    private RequestFromSink request;

    //This is an expensive object. Creating 1 for thread an at the beginning.
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private int numberOfEmptyResponse;

    private ListenableFuture<ResponseFromQueueSource> future;

    PerformRequestAndSaveFile(String endpointURL) {
    	
        this.sinkResponseAsyncStub = Clients.newClient(
                "gproto+http://" + endpointURL,
                GetMessageFromQueueGrpc.GetMessageFromQueueFutureStub.class);

        this.request = RequestFromSink.newBuilder().build();
        this.numberOfEmptyResponse = 0;
        
    }


    @Override
    public void run() {

        while (numberOfEmptyResponse <= 5) {

            this.future = sinkResponseAsyncStub.getItem(request);

            Futures.addCallback(future, new FutureCallback<ResponseFromQueueSource>() {
                @Override
                public void onSuccess(@NullableDecl ResponseFromQueueSource result) {
                	
                	log.info("Received {} files, @ {}", result.getFileList().size(), System.currentTimeMillis());
                	
                    List<ByteString> fileList = result.getFileList();

                    if(fileList == null || fileList.size() == 0) {
                        log.warn("Cannot fetch anymore items - Queue is Empty - #{}", numberOfEmptyResponse);
                        numberOfEmptyResponse++;
                    }

/*
        Note for future dev who tries this: Don't, Parallel only makes it slower.
        I benchmarked!
                    fileList.parallelStream().forEach(byteString -> {
                        ResponseJsonPojo responseJsonPojo = null;
                        try {
                            responseJsonPojo = objectMapper.readValue(byteString.toStringUtf8(), ResponseJsonPojo.class);
                            objectMapper.writeValue(new File(responseJsonPojo.getMessageId() + ".json"), responseJsonPojo);
                        } catch (Exception e) {
                            log.error("Error while parsing JSON / Writing to file for JSON - ", byteString.toStringUtf8(), e);
                        }

                    });
*/
                    else {
                    	numberOfEmptyResponse = 0;
//                        for (ByteString byteString : fileList) {
//
//                            try {
//                                ResponseJsonPojo responseJsonPojo = objectMapper.readValue(byteString.toStringUtf8(), ResponseJsonPojo.class);
//                                objectMapper.writeValue(new File(responseJsonPojo.getMessageId() + ".json"), responseJsonPojo);
//                            } catch (Exception e) {
//                                log.error("Error while parsing JSON / Writing to file for JSON - ", byteString.toStringUtf8(), e);
//                            }
//
//                        }

                        fileList.parallelStream().forEach(byteString -> {
                            ResponseJsonPojo responseJsonPojo = null;
                            try {
                                responseJsonPojo = objectMapper.readValue(byteString.toStringUtf8(), ResponseJsonPojo.class);
                                objectMapper.writeValue(new File("/data/" + responseJsonPojo.getMessageId() + ".json"), responseJsonPojo);
                            } catch (Exception e) {
                                log.error("Error while parsing JSON / Writing to file for JSON - ", byteString.toStringUtf8(), e);
                            }

                        });
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    log.error("Error getting response from endpoint, Reason:", t);
                }
            }, MoreExecutors.directExecutor());

            try {
                ResponseFromQueueSource response = future.get();
            } catch (InterruptedException e) {
                log.error("Cannot get response from GRPC endpoint, FUTURE.get() Interrupted", e);
            } catch (ExecutionException e) {
                log.error("Error executing future.get() for GRPC endpoint, Reason: ", e);
            }
        }

        if(numberOfEmptyResponse == 5)
            log.warn("Killing thread - {} due to inactivity", Thread.currentThread());

    }
}
