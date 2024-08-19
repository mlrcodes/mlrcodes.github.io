package com.miguel.raffles.Payments;

import lombok.Builder;

@Builder
public record ReservationRequest(
        Integer orderId,

        Integer raffleId,

        Integer ticketsQuantity
) { }

