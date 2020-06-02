package com.example.webshop.models.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ProductFieldsErrorResponseModel extends ErrorResponseModel {

    private String nameErrorMessage;
    private String descriptionErrorMessage;
    private String priceErrorMessage;
    private String stockErrorMessage;

}
