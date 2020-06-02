package com.example.webshop.models.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ProductFieldsValidationErrorResponseModel extends ValidationErrorResponseModel {

    private String nameErrorMessage;
    private String descriptionErrorMessage;
    private String priceErrorMessage;
    private String stockErrorMessage;

}
