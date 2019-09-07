package server;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.grpc.GrpcServiceBuilder;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.server.docs.DocService;

public class ProducerServer {

    public static void main(String args[]) {

        ServerBuilder sb = new ServerBuilder();
        sb.http(4242);          // Set port for Netty

        ProducerEndpointImpl producerEndpoint = new ProducerEndpointImpl("/Users/anirudhr/Desktop/sampleData3Sept2019");

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


}
