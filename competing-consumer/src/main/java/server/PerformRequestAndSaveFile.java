package server;

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
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PerformRequestAndSaveFile implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(PerformRequestAndSaveFile.class);

    private GetMessageFromQueueGrpc.GetMessageFromQueueFutureStub sinkResponseAsyncStub;
    private RequestFromSink request;

    //This is an expensive object. Creating 1 for thread an at the beginning.
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ListenableFuture<ResponseFromQueueSource> future;
    private boolean isQueueEmpty;


    PerformRequestAndSaveFile() {
        this.sinkResponseAsyncStub = Clients.newClient(
                "gproto+http://127.0.0.1:4242/",
                GetMessageFromQueueGrpc.GetMessageFromQueueFutureStub.class);

        this.request = RequestFromSink.newBuilder().build();
        isQueueEmpty = false;
    }


    @Override
    public void run() {

        while (!isQueueEmpty) {

            this.future = sinkResponseAsyncStub.getItem(request);

            Futures.addCallback(future, new FutureCallback<ResponseFromQueueSource>() {
                @Override
                public void onSuccess(@NullableDecl ResponseFromQueueSource result) {
                    List<ByteString> fileList = result.getFileList();

                    if(fileList == null || fileList.size() == 0) {
                        log.warn("Cannot fetch anymore items - Queue is Empty");
                        isQueueEmpty = true;
                    }

                    for (ByteString byteString : fileList) {

                        try {
                            ResponseJsonPojo responseJsonPojo = objectMapper.readValue(byteString.toStringUtf8(), ResponseJsonPojo.class);
                            objectMapper.writeValue(new File(responseJsonPojo.getMessageId() + ".json"), responseJsonPojo);
                        } catch (Exception e) {
                            log.error("Error while parsing JSON / Writing to file for JSON - ", byteString.toStringUtf8(), e);
                        }

                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
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

    }
}
