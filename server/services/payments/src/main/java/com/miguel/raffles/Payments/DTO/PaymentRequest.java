package com.miguel.raffles.Payments.DTO;

import lombok.Builder;

@Builder
public record PaymentRequest(

        Integer orderId,

        Integer raffleId,

        String paymentMethod,

        CustomerDTO customer,

        Integer ticketsQuantity

) {
}