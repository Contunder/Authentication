package com.microservice.authentification.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AuthentificationAPIException extends RuntimeException {

    @Getter
    private final HttpStatus status;
    private final String message;

    public AuthentificationAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
