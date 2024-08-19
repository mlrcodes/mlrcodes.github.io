package com.miguel.raffles.Customers.DTO;

import lombok.Builder;

@Builder
public record CustomerResponse(

        Integer id,

        String stripeId,

        String name,

        String email,

        String phoneNumber
) {
}
