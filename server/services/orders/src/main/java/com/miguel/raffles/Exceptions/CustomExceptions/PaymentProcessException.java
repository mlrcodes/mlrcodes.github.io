package com.miguel.raffles.Exceptions.CustomExceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
public class PaymentProcessException extends RuntimeException {
    private final String msg;
}
