package com.miguel.raffles.Kafka.Order;

import lombok.Builder;

public record CustomerDTO(

        Integer id,

        String stripeId,

        String name,

        String email,

        String phoneNumber
) {
}