package server;

import com.google.protobuf.ByteString;
import com.source.producer.ProducerResponse;
import com.source.producer.ProducerServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerEndpointImpl extends ProducerServiceGrpc.ProducerServiceImplBase implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ProducerEndpointImpl.class);
    private String path;
    private ArrayBlockingQueue<byte[]> arrayBlockingQueue;

    ProducerEndpointImpl(String path) {
        this.path = path;
        this.arrayBlockingQueue = new ArrayBlockingQueue<byte[]>(this.getSafeSizeForQueue());
    }

    @Override
    public void run() {
        Path sourceFolder = Paths.get(this.path);
        DirectoryStream<Path> stream = null;
        try {
            stream = Files.newDirectoryStream(sourceFolder); //TODO: Parallelize this

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
    public void getItem(com.source.producer.ProducerRequest request,
                        io.grpc.stub.StreamObserver<com.source.producer.ProducerResponse> responseObserver) {

        ProducerResponse response = ProducerResponse.newBuilder().
                setFile(ByteString.copyFrom(this.arrayBlockingQueue.poll())).
                build();
        log.info("Queue size is - {}", this.arrayBlockingQueue.size());
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
        log.info("Setting the queue size to - {}", safeQueueSize);
        return safeQueueSize;
    }

}
