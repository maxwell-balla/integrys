package com.integrys.backend.exceptions;

public class ValidationError {
    public String property;
    public String message;

    public ValidationError(String property, String message) {
        this.property = property;
        this.message = message;
    }
}
