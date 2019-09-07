package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerServer {

    private static final Logger log = LoggerFactory.getLogger(ConsumerServer.class);
    private static final int idealNumberOfThreads = (Runtime.getRuntime().availableProcessors() + 1);

    public static void main(String[] args) throws InterruptedException {

        log.info("Crawling parallely using {} threads", idealNumberOfThreads);

        long startTime = System.nanoTime();

        ExecutorService executorService = Executors.newFixedThreadPool(idealNumberOfThreads);

        for(int i = 0; i < idealNumberOfThreads; i++) {
            executorService.execute(new PerformRequestAndSaveFile());
        }

        executorService.shutdown();

        while( ! executorService.isTerminated() ) {

        }

       log.info("Total time taken - " + (System.nanoTime() - startTime) + " ns");
    }

}
