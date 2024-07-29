package com.example.featuretest.core.authorization;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException(Exception e) {
        super("Authorization process failed", e);
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
