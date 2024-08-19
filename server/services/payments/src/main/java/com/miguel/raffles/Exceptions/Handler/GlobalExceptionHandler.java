package com.miguel.raffles.Exceptions.Handler;

import com.miguel.raffles.Exceptions.CustomExceptions.NotificationException;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> handleStripeException(StripeException exp) {
        return ResponseEntity
                .status(exp.getStatusCode())
                .body(exp.getMessage());
    }

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(NotificationException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Errors> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Map<String, String> errors = new HashMap<>();

        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError)error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new Errors(errors));
    }

}
