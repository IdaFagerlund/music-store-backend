package com.example.webshop.models;

public class ErrorModel {

    private String error;

    public ErrorModel() {
    }

    public ErrorModel(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
