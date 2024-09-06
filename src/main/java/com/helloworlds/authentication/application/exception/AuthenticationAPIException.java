package com.helloworlds.authentication.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AuthenticationAPIException extends RuntimeException {

    @Getter
    private final HttpStatus status;
    private final String message;

    public AuthenticationAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
