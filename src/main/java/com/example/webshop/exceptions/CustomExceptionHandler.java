package com.example.webshop.exceptions;

import com.example.webshop.models.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorModel> handleValidationError(ValidationException e) {
        return ResponseEntity.status(400).body(new ErrorModel(e.getMessage()));
    }

}
