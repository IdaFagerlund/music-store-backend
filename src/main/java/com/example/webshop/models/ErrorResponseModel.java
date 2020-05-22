package com.example.webshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class ErrorResponseModel {

    private String error;

    public ErrorResponseModel(String error) {
        this.error = error;
    }

}
