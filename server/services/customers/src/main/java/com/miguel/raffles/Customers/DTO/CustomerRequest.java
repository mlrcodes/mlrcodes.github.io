package com.miguel.raffles.Customers.DTO;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
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
