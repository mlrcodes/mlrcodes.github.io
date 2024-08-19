package com.miguel.raffles.Tickets.DTO;

import lombok.Builder;

@Builder
public record PurchaseTicketResponse(
        Integer id,
        Long ticketNumber,
        Integer raffleId
) {
}