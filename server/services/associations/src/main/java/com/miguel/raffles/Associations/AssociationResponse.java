package com.miguel.raffles.Associations;

import lombok.Builder;

@Builder
public record AssociationResponse(
        Integer id,

        String name,

        String email,

        String phoneNumber,

        String city,

        String zipCode
) {
}
