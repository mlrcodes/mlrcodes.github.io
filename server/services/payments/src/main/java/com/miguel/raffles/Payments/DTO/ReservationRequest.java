package com.miguel.raffles.Payments.DTO;

public record ReservationRequest(
        Integer orderId,

        Integer raffleId,

        Integer ticketsQuantity
) { }

