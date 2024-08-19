package com.miguel.raffles.Kafka.Payment;

import lombok.Builder;

@Builder
public record PaymentConfirmation(

        Integer paymentId,
        String orderReference,
        String paymentMethod,
        Double total,
        String customerName,
        String customerEmail
) {
}
