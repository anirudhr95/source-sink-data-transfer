// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProducerConsumerContract.proto

package com.sink.consumer;

public final class Consumer {
  private Consumer() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_consumergrpc_ProducerResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_consumergrpc_ProducerResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_consumergrpc_ProducerRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_consumergrpc_ProducerRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036ProducerConsumerContract.proto\022\014consum" +
      "ergrpc\" \n\020ProducerResponse\022\014\n\004file\030\001 \001(\014" +
      "\"\021\n\017ProducerRequest2]\n\017ProducerService\022J" +
      "\n\007getItem\022\035.consumergrpc.ProducerRequest" +
      "\032\036.consumergrpc.ProducerResponse\"\000B!\n\021co" +
      "m.sink.consumerB\010ConsumerH\001P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_consumergrpc_ProducerResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_consumergrpc_ProducerResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_consumergrpc_ProducerResponse_descriptor,
        new java.lang.String[] { "File", });
    internal_static_consumergrpc_ProducerRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_consumergrpc_ProducerRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_consumergrpc_ProducerRequest_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
