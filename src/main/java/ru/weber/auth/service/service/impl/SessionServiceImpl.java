package ru.weber.auth.service.service.impl;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.weber.auth.service.model.Session;
import ru.weber.auth.service.service.SessionManager;
import ru.weber.auth.service.service.grpc.SessionServiceGrpc;
import ru.weber.auth.service.service.grpc.SessionServiceOuterClass;

@AllArgsConstructor
@Log4j2
@GrpcService
public class SessionServiceImpl extends SessionServiceGrpc.SessionServiceImplBase {
    private final SessionManager sessionManager;

    @Override
    public void getSession(SessionServiceOuterClass.GetSessionRequest request, StreamObserver<SessionServiceOuterClass.GetSessionResponse> responseObserver) {
        String token = request.getToken();
        Session session = sessionManager.getSession(token);
        if (session == null) session = sessionManager.create(token);
        responseObserver.onNext(SessionServiceOuterClass.GetSessionResponse
                .newBuilder()
                .putAllMetaInfo(session.getMetaInfo())
                .build());
        responseObserver.onCompleted();
        log.debug("Response ok for user token: {}", token);
    }

    @Override
    public void invalidateSession(SessionServiceOuterClass.SetSessionInvalidRequest request, StreamObserver<Empty> responseObserver) {
        sessionManager.invalidateSession(request.getToken());
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
        log.debug("Session: {} was invalidated", request.getToken());
    }
}