package com.miguel.raffles.Kafka.Order;

import lombok.Builder;

import java.util.Set;

@Builder
public record OrderConfirmation(

        Integer orderId,
        String orderReference,

        CustomerDTO customer,

        PaymentData paymentData,

        Set<PurchasedTickets> tickets
) {
}