package server;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import com.linecorp.armeria.client.Clients;
import com.sink.queue.GetMessageFromQueueGrpc;
import com.sink.queue.GetMessageFromQueueGrpc.GetMessageFromQueueBlockingStub;
import com.sink.queue.GetMessageFromQueueGrpc.GetMessageFromQueueStub;
import com.sink.queue.RequestFromSink;
import com.sink.queue.ResponseFromQueueSource;

import io.grpc.stub.StreamObserver;

public class PerformRequestAndSaveFile implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(PerformRequestAndSaveFile.class);

    private GetMessageFromQueueGrpc.GetMessageFromQueueBlockingStub getMessageFromQueueStub;

    private RequestFromSink request;

    private StreamObserver<ResponseFromQueueSource> responseStream;

    //This is an expensive object. Creating 1 for thread an at the beginning.
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ListenableFuture<ResponseFromQueueSource> future;
    private boolean isQueueEmpty;


    PerformRequestAndSaveFile(String endpointURL) {
    	
    	this.getMessageFromQueueStub = Clients.newClient(
                "gproto+http://" + endpointURL,
                GetMessageFromQueueBlockingStub.class);


        this.request = RequestFromSink.newBuilder().build();
//        this.responseStream = this.getStreamObserver();
        isQueueEmpty = false;

    }



    @Override
    public void run() {

        while( !isQueueEmpty ) {

            Iterator<ResponseFromQueueSource> responseIterator = this.getMessageFromQueueStub.getItem(request);

            while(responseIterator.hasNext()) {

                ResponseFromQueueSource responseFromQueueSource = responseIterator.next();
                List<ByteString> fileList = responseFromQueueSource.getFileList();
                
                if(fileList == null || fileList.size() == 0) {
                	isQueueEmpty = true;
                	continue;
                }
                
                for(ByteString file : fileList) {
                    ResponseJsonPojo responseJsonPojo = null;
                    try {
                        responseJsonPojo = objectMapper.readValue( file.toStringUtf8(), ResponseJsonPojo.class );
                        objectMapper.writeValue(new File(responseJsonPojo.getMessageId() + ".json"), responseJsonPojo);
                    } catch (Exception e) {
                        log.error("Error while parsing JSON / Writing to file for JSON - ", file.toStringUtf8(), e);
                    }

                }

            }


        }
    	

    }

//    private StreamObserver<ResponseFromQueueSource> getStreamObserver() {
//        return new StreamObserver<ResponseFromQueueSource>() {
//
//            @Override
//            public void onNext(ResponseFromQueueSource result) {
//
//                List<ByteString> fileList = result.getFileList();
//
//                if(fileList == null || fileList.size() == 0) {
//                    log.warn("Cannot fetch anymore items - Queue is Empty");
//                    isQueueEmpty = true;
//                }
//
//
//                for (ByteString byteString : fileList) {
//
//                    try {
//                        ResponseJsonPojo responseJsonPojo = objectMapper.readValue(byteString.toStringUtf8(), ResponseJsonPojo.class);
//                        objectMapper.writeValue(new File(responseJsonPojo.getMessageId() + ".json"), responseJsonPojo);
//
//                    } catch (Exception e) {
//                        log.error("Error while parsing JSON / Writing to file for JSON - ", byteString.toStringUtf8(), e);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                log.error("Error while getting STREAMED response - ", t);
//                isQueueEmpty = true;
//            }
//
//            @Override
//            public void onCompleted() {
//                isQueueEmpty = true;
//                log.info("Finished getting STREAMED response from gRPC endpoint, " +
//                        "All files MUST be transffered to local disk");
//            }
//
//        };
//    }
}
