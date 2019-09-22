package server;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import com.sink.queue.GetMessageFromQueueGrpc;
import com.sink.queue.GetMessageFromQueueGrpc.GetMessageFromQueueBlockingStub;
import com.sink.queue.RequestFromSink;
import com.sink.queue.ResponseFromQueueSource;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PerformRequestAndSaveFile implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(PerformRequestAndSaveFile.class);

	private ManagedChannel managedChannel;

	private GetMessageFromQueueBlockingStub getMessageFromQueueBlockingStub;
	private RequestFromSink request;

	// This is an expensive object. Creating 1 for thread an at the beginning.
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private String endpointURL;

	PerformRequestAndSaveFile(String endpointURL) {

		this.endpointURL = endpointURL;
//		managedChannel = ManagedChannelBuilder.forAddress(endpointURL, 4242).usePlaintext().build();
//		this.getMessageFromQueueBlockingStub = GetMessageFromQueueGrpc.newBlockingStub(managedChannel);
//		this.request = RequestFromSink.newBuilder().build();

	}

	@Override
	public void run() {

		Iterator<ResponseFromQueueSource> responseIterator;
		// Try and use ExecutorServiceCompletion and parallelize this

		while (true) {

			managedChannel = ManagedChannelBuilder.forAddress(this.endpointURL, 4242).usePlaintext().build();
			this.getMessageFromQueueBlockingStub = GetMessageFromQueueGrpc.newBlockingStub(managedChannel);
			this.request = RequestFromSink.newBuilder().build();

			try {
				responseIterator = getMessageFromQueueBlockingStub.getItem(request);
//			log.info("Received - {} @ {}", responseIterator, System.currentTimeMillis());

				while (responseIterator.hasNext()) {

					ResponseFromQueueSource responseFromQueueSource = responseIterator.next();
					List<ByteString> fileList = responseFromQueueSource.getFileList();

					for (ByteString byteString : fileList) {

						try {
							ResponseJsonPojo responseJsonPojo = objectMapper.readValue(byteString.toStringUtf8(),
									ResponseJsonPojo.class);
							objectMapper.writeValue(new File("/data/" + responseJsonPojo.getMessageId() + ".json"),
									responseJsonPojo);
						} catch (Exception e) {
							log.error("Error while parsing JSON / Writing to file for JSON - ",
									byteString.toStringUtf8(), e);
						}
					}

//              fileList.parallelStream().forEach(byteString -> {
//              ResponseJsonPojo responseJsonPojo = null;
//              try {
//                  responseJsonPojo = objectMapper.readValue(byteString.toStringUtf8(), ResponseJsonPojo.class);
//                  objectMapper.writeValue(new File("/data/" + responseJsonPojo.getMessageId() + ".json"), responseJsonPojo);
//              } catch (Exception e) {
//                  log.error("Error while parsing JSON / Writing to file for JSON - ", byteString.toStringUtf8(), e);
//              }
//
//          });

				}

			} catch (Exception e) {
				log.error("Error - {}", e);
			} finally {
				this.managedChannel.shutdown();
			}

		}
	}

}
