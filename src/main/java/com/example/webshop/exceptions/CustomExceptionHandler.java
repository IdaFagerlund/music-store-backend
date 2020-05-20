package com.example.webshop.exceptions;

import com.example.webshop.models.ErrorResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseModel> handleValidationException(ValidationException e) {
        return ResponseEntity.status(400).body(new ErrorResponseModel(e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponseModel(e.getMessage()));
    }

}
