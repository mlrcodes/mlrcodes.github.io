package com.miguel.raffles.Payments;

import lombok.Getter;

public record PaymentResponse(

        Integer id,

        String stripePaymentId,

        String stripeClientSecret,

        String paymentMethod,

        Double total

) {
}
