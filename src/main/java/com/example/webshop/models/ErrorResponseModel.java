package com.example.webshop.models;

public class ErrorResponseModel {

    private String error;

    public ErrorResponseModel(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
