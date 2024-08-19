package com.miguel.raffles.Kafka.Order;

public record PaymentData(

        Integer id,

        String stripePaymentId,

        String paymentMethod,

        Double total

) {
}
