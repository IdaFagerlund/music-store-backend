package com.example.webshop.models.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class UserFieldsValidationErrorResponseModel extends ValidationErrorResponseModel {

    private String emailErrorMessage;
    private String usernameErrorMessage;
    private String passwordErrorMessage;

}
