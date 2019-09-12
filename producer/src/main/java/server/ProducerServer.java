package server;

import com.google.gson.Gson;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.grpc.GrpcServiceBuilder;
import io.grpc.ServerInterceptors;
import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import me.dinowernli.grpc.prometheus.Configuration;
import me.dinowernli.grpc.prometheus.MonitoringServerInterceptor;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static server.ProducerEndpointImpl.idealBatchSize;
import static server.ProducerEndpointImpl.lastBatchSize;
import static spark.Spark.get;

public class ProducerServer {
	
	
	private static final Logger log = LoggerFactory.getLogger(ProducerServer.class);

    private static final CollectorRegistry collectorRegistry = new CollectorRegistry();

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

        MonitoringServerInterceptor monitoringInterceptor =
                MonitoringServerInterceptor.create(Configuration.cheapMetricsOnly().withCollectorRegistry(collectorRegistry));

        Thread thread = new Thread(producerEndpoint);
        thread.start();
        // Starts reading from directory and puts in the queue

        sb.service(
                new GrpcServiceBuilder()
                        .addService(ServerInterceptors.intercept(producerEndpoint.bindService(), monitoringInterceptor))
                        .build()
        );


        sb.serviceUnder("/docs", new DocService());

        /* Docservice API endpoint
            Also acts like postman (But for GRPC)
        */


        Server server = sb.build();
        server.start().join();


        get("/metrics", (req, res) -> {
            res.type("application/json");

            Enumeration<Collector.MetricFamilySamples> samples = findAllRecordedMetricOrThrow();
            ArrayList<Collector.MetricFamilySamples> familySamplesArrayList = Collections.list(samples);

            return new Gson().toJsonTree(setMetricList(familySamplesArrayList));
        });

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

    private static Collector.MetricFamilySamples findRecordedMetricOrThrow(String name) {
        return RegistryHelper.findRecordedMetricOrThrow(name, collectorRegistry);
    }

    static Enumeration<Collector.MetricFamilySamples> findAllRecordedMetricOrThrow() {
        return RegistryHelper.findRecordedMetric(collectorRegistry);
    }

    public static List<Metric> setMetricList(List<Collector.MetricFamilySamples> sampleMetrics) {
        List<Metric> metricList = new ArrayList<>();

        for(Collector.MetricFamilySamples metricFamilySamples : sampleMetrics){

            double sampleSize = 0;

            if( metricFamilySamples.samples.size()>0 )
                 sampleSize = metricFamilySamples.samples.get(0).value * idealBatchSize - (idealBatchSize - lastBatchSize);

            Metric metric = new Metric(metricFamilySamples.name, metricFamilySamples.type.toString(), sampleSize);
            metricList.add(metric);

        }
        return metricList;
    }


    public static class Metric{
        String name;
        String type;
        double value;

        public Metric(String name, String type, double value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

    }

}
