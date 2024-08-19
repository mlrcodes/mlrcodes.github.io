package com.miguel.raffles.Customers;

import jakarta.validation.constraints.NotBlank;
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