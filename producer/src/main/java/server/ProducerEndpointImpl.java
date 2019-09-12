package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.ByteString;
import com.source.queue.GetMessageFromQueueGrpc;
import com.source.queue.ResponseFromQueueSource;

public class ProducerEndpointImpl extends GetMessageFromQueueGrpc.GetMessageFromQueueImplBase {

    private static final Logger log = LoggerFactory.getLogger(ProducerEndpointImpl.class);

    protected static final int idealBatchSize = 1;        // Try batching and send files
    protected static int lastBatchSize = 0;        // Try batching and send files

    @Override
    public void getItem(com.source.queue.RequestFromSink request,
                        io.grpc.stub.StreamObserver<com.source.queue.ResponseFromQueueSource> responseObserver) {

        ResponseFromQueueSource.Builder responseBuilderObj = ResponseFromQueueSource.newBuilder();

        for(int i = 0 ; i < idealBatchSize; i++) {
            byte[] file = InternalQueueImplementation.getFileAsBytesFromQueue();

            if (file == null) {
            	break;
            }
                

            responseBuilderObj.addFile(ByteString.copyFrom(file));
        }
        
        lastBatchSize = responseBuilderObj.getFileCount();

        ResponseFromQueueSource response = responseBuilderObj.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



}
