package com.miguel.raffles.Exceptions.Handler;

import com.miguel.raffles.Exceptions.CustomExceptions.BusinessException;
import com.miguel.raffles.Exceptions.CustomExceptions.RaffleNotFoundException;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RaffleNotFoundException.class)
    public ResponseEntity<String> HandleCustomerNotFoundException(RaffleNotFoundException exp) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(exp.getMsg());
    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> HandleCustomerNotFoundException(StripeException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Map<String, String> errors = new HashMap<>();

        exp.getBindingResult().getAllErrors()
                        .forEach(error -> {
                            String fieldName = ((FieldError)error).getField();
                            String errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> HandleBusinessException(BusinessException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMsg());
    }
}
