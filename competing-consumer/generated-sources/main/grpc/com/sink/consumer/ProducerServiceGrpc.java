package com.sink.consumer;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.23.0)",
    comments = "Source: ProducerConsumerContract.proto")
public final class ProducerServiceGrpc {

  private ProducerServiceGrpc() {}

  public static final String SERVICE_NAME = "consumergrpc.ProducerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.sink.consumer.ProducerRequest,
      com.sink.consumer.ProducerResponse> getGetItemMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getItem",
      requestType = com.sink.consumer.ProducerRequest.class,
      responseType = com.sink.consumer.ProducerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sink.consumer.ProducerRequest,
      com.sink.consumer.ProducerResponse> getGetItemMethod() {
    io.grpc.MethodDescriptor<com.sink.consumer.ProducerRequest, com.sink.consumer.ProducerResponse> getGetItemMethod;
    if ((getGetItemMethod = ProducerServiceGrpc.getGetItemMethod) == null) {
      synchronized (ProducerServiceGrpc.class) {
        if ((getGetItemMethod = ProducerServiceGrpc.getGetItemMethod) == null) {
          ProducerServiceGrpc.getGetItemMethod = getGetItemMethod =
              io.grpc.MethodDescriptor.<com.sink.consumer.ProducerRequest, com.sink.consumer.ProducerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getItem"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sink.consumer.ProducerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sink.consumer.ProducerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProducerServiceMethodDescriptorSupplier("getItem"))
              .build();
        }
      }
    }
    return getGetItemMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProducerServiceStub newStub(io.grpc.Channel channel) {
    return new ProducerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProducerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ProducerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProducerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ProducerServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ProducerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getItem(com.sink.consumer.ProducerRequest request,
        io.grpc.stub.StreamObserver<com.sink.consumer.ProducerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetItemMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetItemMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.sink.consumer.ProducerRequest,
                com.sink.consumer.ProducerResponse>(
                  this, METHODID_GET_ITEM)))
          .build();
    }
  }

  /**
   */
  public static final class ProducerServiceStub extends io.grpc.stub.AbstractStub<ProducerServiceStub> {
    private ProducerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProducerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProducerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProducerServiceStub(channel, callOptions);
    }

    /**
     */
    public void getItem(com.sink.consumer.ProducerRequest request,
        io.grpc.stub.StreamObserver<com.sink.consumer.ProducerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetItemMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProducerServiceBlockingStub extends io.grpc.stub.AbstractStub<ProducerServiceBlockingStub> {
    private ProducerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProducerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProducerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProducerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.sink.consumer.ProducerResponse getItem(com.sink.consumer.ProducerRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetItemMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProducerServiceFutureStub extends io.grpc.stub.AbstractStub<ProducerServiceFutureStub> {
    private ProducerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProducerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProducerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProducerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sink.consumer.ProducerResponse> getItem(
        com.sink.consumer.ProducerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetItemMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ITEM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProducerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProducerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ITEM:
          serviceImpl.getItem((com.sink.consumer.ProducerRequest) request,
              (io.grpc.stub.StreamObserver<com.sink.consumer.ProducerResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ProducerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProducerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.sink.consumer.Consumer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProducerService");
    }
  }

  private static final class ProducerServiceFileDescriptorSupplier
      extends ProducerServiceBaseDescriptorSupplier {
    ProducerServiceFileDescriptorSupplier() {}
  }

  private static final class ProducerServiceMethodDescriptorSupplier
      extends ProducerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProducerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProducerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProducerServiceFileDescriptorSupplier())
              .addMethod(getGetItemMethod())
              .build();
        }
      }
    }
    return result;
  }
}
