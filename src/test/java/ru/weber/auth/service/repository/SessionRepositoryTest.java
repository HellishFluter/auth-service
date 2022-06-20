package ru.weber.auth.service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import ru.weber.auth.service.model.Session;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"})
class SessionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SessionRepository sessionRepository;
    private final String TEST_SESSION_ID = "test session id";

    @Test
    void invalidateSession() {
        Session session = new Session();
        session.setSessionId(TEST_SESSION_ID);
        session.setInvalidated(false);
        assertEquals(0, sessionRepository.findAll().size());
        Long searchId = entityManager.persist(session).getId();
        flushAndClear();
        Session result = sessionRepository.findById(searchId).get();
        assertFalse(result.isInvalidated());
        flushAndClear();

        sessionRepository.invalidateSession(TEST_SESSION_ID);
        result = sessionRepository.findById(searchId).get();
        assertNotNull(result);
        assertTrue(result.isInvalidated());
    }

    @Test
    void getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse_founded() {

        assertEquals(0, sessionRepository.findAll().size());
        Session validSession = new Session();
        validSession.setSessionId(TEST_SESSION_ID);
        validSession.setInvalidated(false);
        validSession.setExpire(Instant.now().plus(1, ChronoUnit.DAYS));
        entityManager.persist(validSession);

        flushAndClear();

        Session foundedSession = sessionRepository.getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse(TEST_SESSION_ID, Instant.now());
        assertEquals(TEST_SESSION_ID, foundedSession.getSessionId());
    }

    @Test
    void getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse_notFounded_badSessionId() {

        assertEquals(0, sessionRepository.findAll().size());
        Session sessionWithBadSessionId = new Session();
        sessionWithBadSessionId.setSessionId(TEST_SESSION_ID + "bad");
        sessionWithBadSessionId.setInvalidated(false);
        sessionWithBadSessionId.setExpire(Instant.now().plus(1, ChronoUnit.DAYS));
        entityManager.persist(sessionWithBadSessionId);

        flushAndClear();

        Session foundedSession = sessionRepository.getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse(TEST_SESSION_ID, Instant.now());
        assertNull(foundedSession);
    }

    @Test
    void getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse_notFounded_isInvalided() {

        assertEquals(0, sessionRepository.findAll().size());
        Session invalidedSession = new Session();
        invalidedSession.setSessionId(TEST_SESSION_ID);
        invalidedSession.setInvalidated(true);
        invalidedSession.setExpire(Instant.now().plus(1, ChronoUnit.DAYS));
        entityManager.persist(invalidedSession);

        flushAndClear();

        Session foundedSession = sessionRepository.getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse(TEST_SESSION_ID, Instant.now());
        assertNull(foundedSession);
    }

    @Test
    void getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse_notFounded_expired() {

        assertEquals(0, sessionRepository.findAll().size());
        Session expiredSession = new Session();
        expiredSession.setSessionId(TEST_SESSION_ID);
        expiredSession.setInvalidated(false);
        expiredSession.setExpire(Instant.now().minus(1, ChronoUnit.DAYS));
        entityManager.persist(expiredSession);

        flushAndClear();

        Session foundedSession = sessionRepository.getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse(TEST_SESSION_ID, Instant.now());
        assertNull(foundedSession);
    }

    private void flushAndClear() {
        this.entityManager.flush();
        this.entityManager.clear();
    }
}