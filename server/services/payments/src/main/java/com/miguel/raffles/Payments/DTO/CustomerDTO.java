package com.miguel.raffles.Payments.DTO;

import lombok.Builder;

@Builder
public record CustomerDTO(

        Integer id,

        String stripeId,

        String name,

        String email,

        String phoneNumber
) {
}