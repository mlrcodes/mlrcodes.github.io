package com.miguel.raffles.Exceptions.Handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.miguel.raffles.Exceptions.CustomExceptions.AssociationNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AssociationNotFoundException.class)
    public ResponseEntity<String> HandleCustomerNotFoundException(AssociationNotFoundException exp) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(exp.getMsg());
    }

}
