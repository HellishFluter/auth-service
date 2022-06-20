package ru.weber.auth.service.exception;

public class JwtInitializationException extends RuntimeException {
    public JwtInitializationException(Throwable e) {
        super("Dont read private key!", e);
    }
}
