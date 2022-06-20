package ru.weber.auth.service.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String clientId) {
        super("Client with id '" + clientId + "' not found.");
    }
}
