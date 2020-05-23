package com.example.webshop.exceptions;

public class ValidationException extends RuntimeException {

    private int status;

    public ValidationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
