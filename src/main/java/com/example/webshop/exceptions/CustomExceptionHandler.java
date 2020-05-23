package com.example.webshop.exceptions;

import com.example.webshop.models.ErrorResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponseModel(e.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseModel> handleBadRequestValidationException(ValidationException e) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponseModel(e.getMessage()));
    }

}
