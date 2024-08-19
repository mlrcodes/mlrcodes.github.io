package com.miguel.raffles.Payments.DTO;

import lombok.Builder;

@Builder
public record TicketDTO(
        Integer id,
        Integer ticketNumber,
        Integer raffleId
) {
}
