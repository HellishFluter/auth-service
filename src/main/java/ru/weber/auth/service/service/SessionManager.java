package ru.weber.auth.service.service;

import ru.weber.auth.service.model.Session;

public interface SessionManager {
    Session create(String token);
    Session getSession(String sessionId);
    void invalidateSession(String sessionId);
}
