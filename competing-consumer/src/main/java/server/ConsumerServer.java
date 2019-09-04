package server;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.linecorp.armeria.client.Clients;
import com.sink.queue.GetMessageFromQueueGrpc.*;
import com.sink.queue.*;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.ExecutionException;

public class ConsumerServer {

    public static void main(String[] args) {

        GetMessageFromQueueFutureStub sinkResponseAsyncStub = Clients.newClient(
                "gproto+http://127.0.0.1:4242/",
                GetMessageFromQueueFutureStub.class);

        RequestFromSink request = RequestFromSink.newBuilder().build();
        ListenableFuture<ResponseFromQueueSource> future = sinkResponseAsyncStub.getItem(request);

        Futures.addCallback(future, new FutureCallback<ResponseFromQueueSource>() {
            @Override
            public void onSuccess(@NullableDecl ResponseFromQueueSource result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, MoreExecutors.directExecutor());

        try {
            ResponseFromQueueSource response = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
