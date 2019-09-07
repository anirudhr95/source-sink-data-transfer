package server;

import com.google.protobuf.ByteString;
import com.source.queue.ResponseFromQueueSource;
import com.source.queue.GetMessageFromQueueGrpc;
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

    private String path;
    private ArrayBlockingQueue<byte[]> arrayBlockingQueue;

    private static final int idealBatchSize = 5;        // Try batching and send files

    ProducerEndpointImpl(String path) {
        this.path = path;
        this.arrayBlockingQueue = new ArrayBlockingQueue<byte[]>(this.getSafeSizeForQueue());
    }

    @Override
    public void run() {

        Path sourceFolder = Paths.get(this.path);
        boolean directoryExists = true;
        DirectoryStream<Path> stream = null;
        try {
            stream = Files.newDirectoryStream(sourceFolder); //TODO: Parallelize this

            for (Path path : stream) {
                arrayBlockingQueue.put(Files.readAllBytes(path));
            }


        } catch (Exception e) {
            log.error("Error while trying to traverse directory - {}", this.path, e);
            log.warn("Cannot find source folder - !!Exiting!!");
            directoryExists = false;

        } finally {

            try {
                stream.close();
            } catch (IOException e) {
                log.error("Error closing DirectoryStream, Reason - ", e);
            }

            if(!directoryExists)
                System.exit(0);
        }

    }

    @Override
    public void getItem(com.source.queue.RequestFromSink request,
                        io.grpc.stub.StreamObserver<com.source.queue.ResponseFromQueueSource> responseObserver) {

        ResponseFromQueueSource.Builder responseBuilderObj = ResponseFromQueueSource.newBuilder();

        for(int i = 0 ; i < this.idealBatchSize; i++) {
            byte[] file = this.arrayBlockingQueue.poll();

            if(file == null)
                break;

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
