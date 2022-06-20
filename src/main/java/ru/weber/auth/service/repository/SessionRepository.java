package ru.weber.auth.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.weber.auth.service.model.Session;

import java.time.Instant;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Session getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse(String sessionId, Instant expire);

    void deleteSessionByExpireBefore(Instant instant);

    @Modifying
    @Query("UPDATE Session s SET s.isInvalidated = true where s.sessionId = :sessionId")
    void invalidateSession(String sessionId);
}
