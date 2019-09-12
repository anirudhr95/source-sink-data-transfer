package server;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ArrayBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author anirudhr Gives you a singleton class that maintains all files ina
 *         queue.
 *
 */

public class InternalQueueImplementation implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(InternalQueueImplementation.class);

    private Path path;
    private static ArrayBlockingQueue<byte[]> arrayBlockingQueue;

    private static volatile InternalQueueImplementation internalQueueImplementation;

    public static InternalQueueImplementation getSingletonQueueObject(Path path) {

        if (internalQueueImplementation == null) {
            synchronized (InternalQueueImplementation.class) {
                if (internalQueueImplementation == null) {
                    log.info("Creating singleton of Queue object for first-time");
                    internalQueueImplementation = new InternalQueueImplementation(path);

                }
            }

        }

        return internalQueueImplementation;
    }

    private InternalQueueImplementation(Path path) {
        this.path = path;
        arrayBlockingQueue = new ArrayBlockingQueue<byte[]>(this.getSafeSizeForQueue());
    }

    @Override
    public void run() {

        DirectoryStream<Path> stream = null;
        try {
            stream = Files.newDirectoryStream(this.path); // TODO: Parallelize this

//            java.util.stream.Stream<Path> s = java.util.stream.StreamSupport.stream(stream.spliterator(), false);

            for (Path path : stream) {
                arrayBlockingQueue.put(Files.readAllBytes(path));
            }
            log.info("---- Completed writing all the files to internal queue ----");

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

    public static ArrayBlockingQueue<byte[]> getQueue() {
        return arrayBlockingQueue;
    }

    public static int getQueueSize() {
        return arrayBlockingQueue.size();
    }

    public static byte[] getFileAsBytesFromQueue() {
        return arrayBlockingQueue.poll();
    }

    private int getSafeSizeForQueue() {
        /*
         * Maximum size of file I notice is 10KB Take the xmxx argument if any, and
         * leave a buffer of 300 MB.
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
