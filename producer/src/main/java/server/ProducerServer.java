package server;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.grpc.GrpcServiceBuilder;
import com.linecorp.armeria.server.docs.DocService;

public class ProducerServer {
	
	
	private static final Logger log = LoggerFactory.getLogger(ProducerServer.class);
	

    public static void main(String args[]) {
    	
    	CommandLine commandLine = getCommandLineParser(args);
    	
    	int portNumber = Integer.valueOf(commandLine.getOptionValue("p", "4242"));
    	String directoryContainingFiles = commandLine.getOptionValue("s");
    	
    	Path path = Paths.get(directoryContainingFiles);
  
    	if(! path.toFile().exists() ) {
    		log.error("{} is not a valid/existing path for a FOLDER on this file-system, "
    				+ "Please re-try with a valid path", path);
    		System.exit(1);
    	}
    	
 
        ServerBuilder sb = new ServerBuilder();
        sb.http(portNumber);          // Set port for Netty

        ProducerEndpointImpl producerEndpoint = new ProducerEndpointImpl(path);

        Thread thread = new Thread(producerEndpoint);
        thread.start();
        // Starts reading from directory and puts in the queue

        sb.service(
                new GrpcServiceBuilder()
                        .addService(producerEndpoint)
                        .build()
        );

        sb.serviceUnder("/docs", new DocService());
        /* Docservice API endpoint
            Also acts like postman (But for GRPC)
        */

        Server server = sb.build();
        server.start().join();
        // DUN DUN DUN!
    }
    
    private static CommandLine getCommandLineParser(String args[]) {
    	
    	Options options = new Options();
    	
    	Option portNumber = new Option("p","port", true, "port number binding for server");
    	portNumber.setRequired(false);
    	options.addOption(portNumber);
    	
    	Option sourceFolder = new Option("s", "source", true, "source folder containing files");
    	sourceFolder.setRequired(true);
    	options.addOption(sourceFolder);
    	
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
