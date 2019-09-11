package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.ByteString;
import com.source.queue.GetMessageFromQueueGrpc;
import com.source.queue.ResponseFromQueueSource;

public class ProducerEndpointImpl extends GetMessageFromQueueGrpc.GetMessageFromQueueImplBase {

	private static final Logger log = LoggerFactory.getLogger(ProducerEndpointImpl.class);


	private static final int idealBatchSize = 5; // Try batching and send files



	@Override
	public void getItem(com.source.queue.RequestFromSink request,
			io.grpc.stub.StreamObserver<com.source.queue.ResponseFromQueueSource> responseObserver) {
		
		long startTime = System.nanoTime();

		ResponseFromQueueSource.Builder responseBuilderObj = ResponseFromQueueSource.newBuilder();

		while (InternalQueueImplementation.getQueueSize() > 0) {

			responseBuilderObj.clearFile();
			
			for (int i = 0; i < this.idealBatchSize; i++) {
				byte[] file = InternalQueueImplementation.getFileAsBytesFromQueue();

				if (file == null || file.length == 0)
					break;

				responseBuilderObj.addFile(ByteString.copyFrom(file));
			}

			ResponseFromQueueSource response = responseBuilderObj.build();
			log.info("Sending response - {}", response);

			responseObserver.onNext(response);
		}
		
		log.info("Completed sending!, {}", (System.nanoTime() - startTime));
		
		responseObserver.onCompleted();

	}


}
