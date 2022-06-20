package ru.weber.auth.service.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.weber.auth.service.dto.TokenDto;
import ru.weber.auth.service.model.Session;
import ru.weber.auth.service.repository.SessionRepository;
import ru.weber.auth.service.service.SessionManager;
import ru.weber.auth.service.service.jwt.JwtService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SessionManagerImplTest {
    @MockBean
    private SessionRepository sessionRepository;
    @MockBean
    private JwtService jwtService;
    private final String FIRST_NAME = "First Name";
    private final String LAST_NAME = "Last Name";
    private final String SESSION_ID = "test session ID";
    private final String TOKEN = "testToken";

    private SessionManager sessionManager;
    @BeforeEach
    void setUp() {
        sessionManager = new SessionManagerImpl(sessionRepository, jwtService);
    }

    @Test
    void create() {
        TokenDto tokenDto = new TokenDto(FIRST_NAME, LAST_NAME, null, Instant.now().plus(1, ChronoUnit.DAYS), SESSION_ID);
        when(jwtService.verifyTokenAndCreateTokenDto(TOKEN)).thenReturn(tokenDto);
        when(sessionRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        Session resultedSession = sessionManager.create(TOKEN);
        assertEquals(resultedSession.getSessionId(), SESSION_ID);
        assertEquals(resultedSession.getMetaInfo().get("FIRST_NAME"), FIRST_NAME);
        assertEquals(resultedSession.getMetaInfo().get("LAST_NAME"), LAST_NAME);
        verify(jwtService, times(1)).verifyTokenAndCreateTokenDto(any());
        verify(sessionRepository, times(1)).save(any());
    }

    @Test
    void getSession_founded() {
        when(sessionRepository.getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse(any(), any())).thenReturn(new Session());
        Session foundedSession = sessionManager.getSession(SESSION_ID);
        assertNotNull(foundedSession);
    }

    @Test
    void getSession_notFounded() {
        when(sessionRepository.getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse(any(), any())).thenReturn(null);
        Session foundedSession = sessionManager.getSession(SESSION_ID);
        assertNull(foundedSession);
    }

    @Test
    void invalidateSession() {
        assertDoesNotThrow(() -> sessionManager.invalidateSession(SESSION_ID));
    }
}