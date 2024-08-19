package com.miguel.raffles.Tickets.DTO;

import com.miguel.raffles.Raffles.DTO.TicketRaffleResponse;
import com.miguel.raffles.Tickets.Ticket;
import com.miguel.raffles.Tickets.TicketStatus;
import lombok.Builder;


@Builder
public record TicketResponse(
        Integer id,

        TicketRaffleResponse raffle,

        Long ticketNumber,

        Double price,

        TicketStatus status,

        Integer customerId
) { }
