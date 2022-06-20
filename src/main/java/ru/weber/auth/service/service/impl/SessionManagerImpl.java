package ru.weber.auth.service.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.weber.auth.service.dto.TokenDto;
import ru.weber.auth.service.model.Session;
import ru.weber.auth.service.repository.SessionRepository;
import ru.weber.auth.service.service.SessionManager;
import ru.weber.auth.service.service.jwt.JwtService;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class SessionManagerImpl implements SessionManager {

    private final SessionRepository sessionRepository;
    private final JwtService jwtService;

    @Transactional
    @Override
    public Session getSession(String sessionId) {
        return sessionRepository.getSessionBySessionIdAndExpireAfterAndIsInvalidatedFalse(sessionId, Instant.now());
    }

    @Transactional
    @Override
    public Session create(String token) {
        TokenDto tokenDto = jwtService.verifyTokenAndCreateTokenDto(token);
        Session session = Session.builder()
                .sessionId(tokenDto.getSessionId())
                .expire(tokenDto.getExpiration())
                .isInvalidated(false)
                .metaInfo(createMetaInfo(tokenDto))
                .build();
        return sessionRepository.save(session);
    }

    private Map<String, String> createMetaInfo(TokenDto tokenDto) {
        Map<String, String> metaInfo = new HashMap<>();
        metaInfo.put("FIRST_NAME", tokenDto.getFirstName());
        metaInfo.put("LAST_NAME", tokenDto.getLastName());
        if (tokenDto.getMiddleName() != null) {
            metaInfo.put("MIDDLE_NAME", tokenDto.getMiddleName());
        }
        return metaInfo;
    }

    @Transactional
    @Override
    public void invalidateSession(String sessionId) {
        sessionRepository.invalidateSession(sessionId);
    }

    @Transactional
    @Scheduled(initialDelay = 10, fixedDelay = 3600000)
    public void deleteExpiredSessions() {
        sessionRepository.deleteSessionByExpireBefore(Instant.now());
    }
}
