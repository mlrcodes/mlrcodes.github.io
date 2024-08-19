package com.miguel.raffles.Kafka.Order;

public record PurchasedTickets(
        Integer id,

        Integer raffleId,

        Long ticketNumber

) {
}
