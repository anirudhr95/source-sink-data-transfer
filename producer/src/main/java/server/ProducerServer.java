package server;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.grpc.GrpcServiceBuilder;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.server.docs.DocService;

public class ProducerServer {

    public static void main(String args[]) {
        ServerBuilder sb = new ServerBuilder();
        sb.http(4242);

        ProducerEndpointImpl producerEndpoint = new ProducerEndpointImpl("/Users/anirudhr/Desktop/sampleData");
        Thread thread = new Thread(producerEndpoint);
        thread.start();

        sb.service(
                new GrpcServiceBuilder()
                        .addService(producerEndpoint)
                        .build()
        );

        sb.serviceUnder("/docs", new DocService());

        Server server = sb.build();
        server.start().join();
    }


}
