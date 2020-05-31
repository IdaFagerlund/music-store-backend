package com.example.webshop.exceptions;

import com.example.webshop.models.errors.ErrorResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseModel> handleValidationException(ValidationException e) {
        return ResponseEntity.status(400).body(e.getErrorResponseModel());
    }

}
