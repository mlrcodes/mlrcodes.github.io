package com.miguel.raffles.Tickets.DTO;

import com.miguel.raffles.Raffles.Raffle;
import com.miguel.raffles.Raffles.RaffleStatus;
import com.miguel.raffles.Tickets.TicketStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record RaffleTicketsResponse(

        Integer id,

        Long ticketNumber,

        Double price,

        TicketStatus status,

        Integer customerId
) {
}
