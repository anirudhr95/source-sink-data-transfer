package server;

import com.google.protobuf.ByteString;
import com.source.queue.GetMessageFromQueueGrpc;
import com.source.queue.ResponseFromQueueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerEndpointImpl extends GetMessageFromQueueGrpc.GetMessageFromQueueImplBase implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ProducerEndpointImpl.class);

    private Path path;
    private ArrayBlockingQueue<byte[]> arrayBlockingQueue;

    protected static final int idealBatchSize = 30;        // Try batching and send files
    protected static int lastBatchSize = 0;        // Try batching and send files

    ProducerEndpointImpl(Path path) {
        this.path = path;
        this.arrayBlockingQueue = new ArrayBlockingQueue<byte[]>(this.getSafeSizeForQueue());
    }


	@Override
    public void run() {

        DirectoryStream<Path> stream = null;
        try {
            stream = Files.newDirectoryStream(this.path); //TODO: Parallelize this

//            java.util.stream.Stream<Path> s = java.util.stream.StreamSupport.stream(stream.spliterator(), false);


            for (Path path : stream) {
                arrayBlockingQueue.put(Files.readAllBytes(path));
            }


        } catch (Exception e) {
            log.error("Error while trying to traverse directory - {}", this.path, e);
        } finally {

            try {
                stream.close();
            } catch (IOException e) {
                log.error("Error closing DirectoryStream, Reason - ", e);
            }
        }

    }

    @Override
    public void getItem(com.source.queue.RequestFromSink request,
                        io.grpc.stub.StreamObserver<com.source.queue.ResponseFromQueueSource> responseObserver) {

        ResponseFromQueueSource.Builder responseBuilderObj = ResponseFromQueueSource.newBuilder();

        for(int i = 0 ; i < idealBatchSize; i++) {
            byte[] file = this.arrayBlockingQueue.poll();

            if (file != null) {
                lastBatchSize = file.length;
            } else {

                break;
            }

            responseBuilderObj.addFile(ByteString.copyFrom(file));
        }

        ResponseFromQueueSource response = responseBuilderObj.build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    private int getSafeSizeForQueue() {
        /* Maximum size of file I notice is 10KB
           Take the xmxx argument if any, and leave a buffer of 300 MB.
         */

        long safeAndUsableMemory = (Runtime.getRuntime().maxMemory() - (300 * 1024 * 1024));
        log.debug("Assigned maximum memory - {}", safeAndUsableMemory);
        int safeQueueSize;
        try {
            safeQueueSize = Math.toIntExact(safeAndUsableMemory / (10 * 1024));
        } catch (ArithmeticException ae) {
            safeQueueSize = 300;
        }
        log.info("Setting the maximum ideal queue size to - {}", safeQueueSize);
        return safeQueueSize;
    }


}
