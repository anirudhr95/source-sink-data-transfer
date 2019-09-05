// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProducerConsumerContract.proto

package com.source.queue;

/**
 * Protobuf type {@code queue.ResponseFromQueueSource}
 */
public  final class ResponseFromQueueSource extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:queue.ResponseFromQueueSource)
    ResponseFromQueueSourceOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ResponseFromQueueSource.newBuilder() to construct.
  private ResponseFromQueueSource(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ResponseFromQueueSource() {
    file_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ResponseFromQueueSource();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ResponseFromQueueSource(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              file_ = new java.util.ArrayList<com.google.protobuf.ByteString>();
              mutable_bitField0_ |= 0x00000001;
            }
            file_.add(input.readBytes());
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        file_ = java.util.Collections.unmodifiableList(file_); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.source.queue.queue.internal_static_queue_ResponseFromQueueSource_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.source.queue.queue.internal_static_queue_ResponseFromQueueSource_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.source.queue.ResponseFromQueueSource.class, com.source.queue.ResponseFromQueueSource.Builder.class);
  }

  public static final int FILE_FIELD_NUMBER = 1;
  private java.util.List<com.google.protobuf.ByteString> file_;
  /**
   * <code>repeated bytes file = 1;</code>
   */
  public java.util.List<com.google.protobuf.ByteString>
      getFileList() {
    return file_;
  }
  /**
   * <code>repeated bytes file = 1;</code>
   */
  public int getFileCount() {
    return file_.size();
  }
  /**
   * <code>repeated bytes file = 1;</code>
   */
  public com.google.protobuf.ByteString getFile(int index) {
    return file_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < file_.size(); i++) {
      output.writeBytes(1, file_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < file_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeBytesSizeNoTag(file_.get(i));
      }
      size += dataSize;
      size += 1 * getFileList().size();
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.source.queue.ResponseFromQueueSource)) {
      return super.equals(obj);
    }
    com.source.queue.ResponseFromQueueSource other = (com.source.queue.ResponseFromQueueSource) obj;

    if (!getFileList()
        .equals(other.getFileList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getFileCount() > 0) {
      hash = (37 * hash) + FILE_FIELD_NUMBER;
      hash = (53 * hash) + getFileList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.source.queue.ResponseFromQueueSource parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.source.queue.ResponseFromQueueSource parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.source.queue.ResponseFromQueueSource parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.source.queue.ResponseFromQueueSource parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.source.queue.ResponseFromQueueSource prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code queue.ResponseFromQueueSource}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:queue.ResponseFromQueueSource)
      com.source.queue.ResponseFromQueueSourceOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.source.queue.queue.internal_static_queue_ResponseFromQueueSource_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.source.queue.queue.internal_static_queue_ResponseFromQueueSource_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.source.queue.ResponseFromQueueSource.class, com.source.queue.ResponseFromQueueSource.Builder.class);
    }

    // Construct using com.source.queue.ResponseFromQueueSource.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      file_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.source.queue.queue.internal_static_queue_ResponseFromQueueSource_descriptor;
    }

    @java.lang.Override
    public com.source.queue.ResponseFromQueueSource getDefaultInstanceForType() {
      return com.source.queue.ResponseFromQueueSource.getDefaultInstance();
    }

    @java.lang.Override
    public com.source.queue.ResponseFromQueueSource build() {
      com.source.queue.ResponseFromQueueSource result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.source.queue.ResponseFromQueueSource buildPartial() {
      com.source.queue.ResponseFromQueueSource result = new com.source.queue.ResponseFromQueueSource(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        file_ = java.util.Collections.unmodifiableList(file_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.file_ = file_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.source.queue.ResponseFromQueueSource) {
        return mergeFrom((com.source.queue.ResponseFromQueueSource)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.source.queue.ResponseFromQueueSource other) {
      if (other == com.source.queue.ResponseFromQueueSource.getDefaultInstance()) return this;
      if (!other.file_.isEmpty()) {
        if (file_.isEmpty()) {
          file_ = other.file_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureFileIsMutable();
          file_.addAll(other.file_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.source.queue.ResponseFromQueueSource parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.source.queue.ResponseFromQueueSource) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.google.protobuf.ByteString> file_ = java.util.Collections.emptyList();
    private void ensureFileIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        file_ = new java.util.ArrayList<com.google.protobuf.ByteString>(file_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated bytes file = 1;</code>
     */
    public java.util.List<com.google.protobuf.ByteString>
        getFileList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(file_) : file_;
    }
    /**
     * <code>repeated bytes file = 1;</code>
     */
    public int getFileCount() {
      return file_.size();
    }
    /**
     * <code>repeated bytes file = 1;</code>
     */
    public com.google.protobuf.ByteString getFile(int index) {
      return file_.get(index);
    }
    /**
     * <code>repeated bytes file = 1;</code>
     */
    public Builder setFile(
        int index, com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureFileIsMutable();
      file_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes file = 1;</code>
     */
    public Builder addFile(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureFileIsMutable();
      file_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes file = 1;</code>
     */
    public Builder addAllFile(
        java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
      ensureFileIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, file_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes file = 1;</code>
     */
    public Builder clearFile() {
      file_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:queue.ResponseFromQueueSource)
  }

  // @@protoc_insertion_point(class_scope:queue.ResponseFromQueueSource)
  private static final com.source.queue.ResponseFromQueueSource DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.source.queue.ResponseFromQueueSource();
  }

  public static com.source.queue.ResponseFromQueueSource getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ResponseFromQueueSource>
      PARSER = new com.google.protobuf.AbstractParser<ResponseFromQueueSource>() {
    @java.lang.Override
    public ResponseFromQueueSource parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ResponseFromQueueSource(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ResponseFromQueueSource> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ResponseFromQueueSource> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.source.queue.ResponseFromQueueSource getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

