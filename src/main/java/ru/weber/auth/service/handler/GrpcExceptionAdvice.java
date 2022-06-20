package ru.weber.auth.service.handler;

import io.grpc.Status;
import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import ru.weber.auth.service.exception.ClientNotFoundException;
import ru.weber.auth.service.exception.JwtInitializationException;

@Log4j2
@GrpcAdvice
public class GrpcExceptionAdvice {

    @GrpcExceptionHandler
    public Status handleInvalidArgument(IllegalArgumentException e) {
        Status status = Status.INVALID_ARGUMENT.withDescription("Invalid argument passed").withCause(e);
        log.error(
                "Error getting user - {}, status code - {}",
                status.getDescription(),
                status.getCode()
        );
        log.trace(status.getCause());
        return status;
    }

    @GrpcExceptionHandler(ClientNotFoundException.class)
    public Status handleClientNotFoundException(ClientNotFoundException e) {
        Status status = Status.INVALID_ARGUMENT.withDescription(e.getMessage()).withCause(e);
        log.debug("{}, status code - {}", status.getDescription(), status.getCode());
        return status;
    }

    @GrpcExceptionHandler(JwtInitializationException.class)
    public Status handleJwtInitializationException(JwtInitializationException e) {
        Status status = Status.INTERNAL.withDescription(e.getMessage()).withCause(e);
        log.debug("{}, status code - {}", status.getDescription(), status.getCode());
        return status;
    }

    @GrpcExceptionHandler(JwtException.class)
    public Status handleJwtException(JwtException e) {
        Status status = Status.INVALID_ARGUMENT.withDescription(e.getMessage()).withCause(e);
        log.debug("{}, status code - {}", status.getDescription(), status.getCode());
        return status;
    }
}
