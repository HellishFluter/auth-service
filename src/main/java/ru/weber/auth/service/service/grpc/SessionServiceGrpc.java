package ru.weber.auth.service.service.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.0)",
    comments = "Source: session_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SessionServiceGrpc {

  private SessionServiceGrpc() {}

  public static final String SERVICE_NAME = "ru.weber.auth.service.service.grpc.SessionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest,
      ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse> getGetSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSession",
      requestType = ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest.class,
      responseType = ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest,
      ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse> getGetSessionMethod() {
    io.grpc.MethodDescriptor<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest, ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse> getGetSessionMethod;
    if ((getGetSessionMethod = SessionServiceGrpc.getGetSessionMethod) == null) {
      synchronized (SessionServiceGrpc.class) {
        if ((getGetSessionMethod = SessionServiceGrpc.getGetSessionMethod) == null) {
          SessionServiceGrpc.getGetSessionMethod = getGetSessionMethod =
              io.grpc.MethodDescriptor.<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest, ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SessionServiceMethodDescriptorSupplier("GetSession"))
              .build();
        }
      }
    }
    return getGetSessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest,
      com.google.protobuf.Empty> getInvalidateSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InvalidateSession",
      requestType = ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest,
      com.google.protobuf.Empty> getInvalidateSessionMethod() {
    io.grpc.MethodDescriptor<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest, com.google.protobuf.Empty> getInvalidateSessionMethod;
    if ((getInvalidateSessionMethod = SessionServiceGrpc.getInvalidateSessionMethod) == null) {
      synchronized (SessionServiceGrpc.class) {
        if ((getInvalidateSessionMethod = SessionServiceGrpc.getInvalidateSessionMethod) == null) {
          SessionServiceGrpc.getInvalidateSessionMethod = getInvalidateSessionMethod =
              io.grpc.MethodDescriptor.<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InvalidateSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new SessionServiceMethodDescriptorSupplier("InvalidateSession"))
              .build();
        }
      }
    }
    return getInvalidateSessionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SessionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SessionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SessionServiceStub>() {
        @java.lang.Override
        public SessionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SessionServiceStub(channel, callOptions);
        }
      };
    return SessionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SessionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SessionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SessionServiceBlockingStub>() {
        @java.lang.Override
        public SessionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SessionServiceBlockingStub(channel, callOptions);
        }
      };
    return SessionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SessionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SessionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SessionServiceFutureStub>() {
        @java.lang.Override
        public SessionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SessionServiceFutureStub(channel, callOptions);
        }
      };
    return SessionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SessionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSession(ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest request,
        io.grpc.stub.StreamObserver<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSessionMethod(), responseObserver);
    }

    /**
     */
    public void invalidateSession(ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInvalidateSessionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSessionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest,
                ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse>(
                  this, METHODID_GET_SESSION)))
          .addMethod(
            getInvalidateSessionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_INVALIDATE_SESSION)))
          .build();
    }
  }

  /**
   */
  public static final class SessionServiceStub extends io.grpc.stub.AbstractAsyncStub<SessionServiceStub> {
    private SessionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SessionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SessionServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSession(ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest request,
        io.grpc.stub.StreamObserver<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSessionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void invalidateSession(ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInvalidateSessionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SessionServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SessionServiceBlockingStub> {
    private SessionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SessionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SessionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse getSession(ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty invalidateSession(ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInvalidateSessionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SessionServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SessionServiceFutureStub> {
    private SessionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SessionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SessionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse> getSession(
        ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSessionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> invalidateSession(
        ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInvalidateSessionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SESSION = 0;
  private static final int METHODID_INVALIDATE_SESSION = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SessionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SessionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SESSION:
          serviceImpl.getSession((ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionRequest) request,
              (io.grpc.stub.StreamObserver<ru.weber.auth.service.service.grpc.SessionServiceOuterClass.GetSessionResponse>) responseObserver);
          break;
        case METHODID_INVALIDATE_SESSION:
          serviceImpl.invalidateSession((ru.weber.auth.service.service.grpc.SessionServiceOuterClass.SetSessionInvalidRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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

  private static abstract class SessionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SessionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.weber.auth.service.service.grpc.SessionServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SessionService");
    }
  }

  private static final class SessionServiceFileDescriptorSupplier
      extends SessionServiceBaseDescriptorSupplier {
    SessionServiceFileDescriptorSupplier() {}
  }

  private static final class SessionServiceMethodDescriptorSupplier
      extends SessionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SessionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SessionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SessionServiceFileDescriptorSupplier())
              .addMethod(getGetSessionMethod())
              .addMethod(getInvalidateSessionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
