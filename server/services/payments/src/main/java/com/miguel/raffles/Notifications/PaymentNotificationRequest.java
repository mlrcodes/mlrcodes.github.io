package com.miguel.raffles.Notifications;

import lombok.Builder;

@Builder
public record PaymentNotificationRequest(

        Integer paymentId,
        String orderReference,
        String paymentMethod,
        Double total,
        String customerName,
        String customerEmail
) {
}
