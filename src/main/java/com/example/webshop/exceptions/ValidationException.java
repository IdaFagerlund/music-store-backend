package com.example.webshop.exceptions;

import com.example.webshop.models.errors.ErrorResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ValidationException extends RuntimeException {

    private ErrorResponseModel errorResponseModel;

}
