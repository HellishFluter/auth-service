package ru.weber.auth.service.service.impl;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.weber.auth.service.dto.TokenDto;
import ru.weber.auth.service.model.Session;
import ru.weber.auth.service.service.SessionManager;
import ru.weber.auth.service.service.grpc.SessionServiceGrpc;
import ru.weber.auth.service.service.grpc.SessionServiceOuterClass;
import ru.weber.auth.service.service.jwt.JwtService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SessionServiceImplTest {

    @MockBean
    private JwtService jwtService;
    @MockBean
    private SessionManager sessionManager;
    private final String TOKEN = "testToken";
    private SessionServiceGrpc.SessionServiceBlockingStub stub;

    @Rule
    public final GrpcCleanupRule grpcCleanupRule = new GrpcCleanupRule();

    @BeforeEach
    void setUp() throws IOException {
        String serverName = InProcessServerBuilder.generateName();
        grpcCleanupRule.register(InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                .addService(new SessionServiceImpl(sessionManager))
                .build().start());
        stub = SessionServiceGrpc.newBlockingStub(
                grpcCleanupRule.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );
    }

    @Test
    void getSession_founded() {
        Session defaultSession = createDefaultSession();
        when(sessionManager.getSession(TOKEN)).thenReturn(defaultSession);

        SessionServiceOuterClass.GetSessionResponse response = stub.getSession(
                SessionServiceOuterClass.GetSessionRequest
                        .newBuilder()
                        .setToken(TOKEN)
                        .build()
        );
        Assertions.assertEquals(
                SessionServiceOuterClass.GetSessionResponse
                        .newBuilder()
                        .putAllMetaInfo(defaultSession.getMetaInfo()).build(),
                response);
        verify(sessionManager, times(1)).getSession(TOKEN);
        verifyNoInteractions(jwtService);
    }

    @Test
    void getSession_newSession() {
        when(jwtService.verifyTokenAndCreateTokenDto(TOKEN)).thenReturn(new TokenDto());
        Session defaultSession = createDefaultSession();
        when(sessionManager.create(any())).thenReturn(defaultSession);

        SessionServiceOuterClass.GetSessionResponse response = stub.getSession(
                SessionServiceOuterClass.GetSessionRequest
                        .newBuilder()
                        .setToken(TOKEN)
                        .build()
        );
        Assertions.assertEquals(
                SessionServiceOuterClass.GetSessionResponse
                        .newBuilder()
                        .putAllMetaInfo(defaultSession.getMetaInfo()).build(),
                response);
        verify(sessionManager, times(1)).create(any());
    }

    @Test
    void invalidateSession() {
        stub.invalidateSession(SessionServiceOuterClass.SetSessionInvalidRequest
                .newBuilder()
                .setToken(TOKEN)
                .build());
        verify(sessionManager, times(1)).invalidateSession(TOKEN);
        verifyNoInteractions(jwtService);

    }

    private Session createDefaultSession() {
        Map<String, String> metaInfo = new HashMap<>();
        metaInfo.put("firstName", "testFirstName");
        metaInfo.put("lastName", "testLastName");

        return Session.builder().
                sessionId(TOKEN)
                .metaInfo(metaInfo)
                .build();
    }
}