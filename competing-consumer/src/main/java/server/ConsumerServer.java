package server;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerServer {

    private static final Logger log = LoggerFactory.getLogger(ConsumerServer.class);

    // If I use availableProcessors() I don't need to worry if the processors are hyper-threaded
    private static final int idealNumberOfThreads = (Runtime.getRuntime().availableProcessors() + 1);

    public static void main(String[] args) throws InterruptedException {
    	
    	CommandLine commandLine = getCommandLineParser(args);
    	String endpoint = commandLine.getOptionValue("e");

        log.info("Using {} threads to get files", idealNumberOfThreads);

        long startTime = System.nanoTime();     // »-(¯`·.·´¯)-> PRECISION BOIS <-(¯`·.·´¯)-«

        ExecutorService executorService = Executors.newFixedThreadPool(idealNumberOfThreads);

        for(int i = 0; i < idealNumberOfThreads; i++) {
            executorService.execute(new PerformRequestAndSaveFile(endpoint));
        }

        executorService.shutdown();

        while( ! executorService.isTerminated() ) {

        }

       log.info("Total time taken - " + (System.nanoTime() - startTime) + " ns");
    }
    
    private static CommandLine getCommandLineParser(String args[]) {
    	
    	Options options = new Options();
    	
    	Option producerEndpoint = new Option("e", "endpoint", true, "Producer endpoint URL");
    	producerEndpoint.setRequired(true);
    	options.addOption(producerEndpoint);
    	
    	CommandLineParser parser = new DefaultParser();
    	HelpFormatter formatter = new HelpFormatter();
    	CommandLine commandLine = null;
    	
    	try {
    		commandLine = parser.parse(options, args);
    	} catch(ParseException pe) {
    		log.error("Error parsing command line arguments - ", pe);
    		formatter.printHelp("utility-name", options);
    		System.exit(1);
    	}
    	
    	return commandLine;
    }

}
