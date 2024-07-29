package com.example.featuretest.core.persona;

public class PersonaNotFoundException extends RuntimeException {
    public PersonaNotFoundException(String message) {
        super(message);
    }

    public PersonaNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
