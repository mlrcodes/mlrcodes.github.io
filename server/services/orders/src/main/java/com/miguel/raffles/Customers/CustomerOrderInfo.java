package com.miguel.raffles.Customers;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record  CustomerOrderInfo(

        @NotBlank(message = "Firstname should not be blank")
        String firstname,

        @NotBlank(message = "Lastname should not be blank")
        String lastname,

        @NotBlank(message = "Email should not be blank")
        String email,

        @NotBlank(message = "Phone number should not be blank")
        String phoneNumber
) {
}
