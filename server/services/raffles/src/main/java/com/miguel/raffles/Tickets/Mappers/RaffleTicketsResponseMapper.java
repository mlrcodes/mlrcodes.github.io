package com.miguel.raffles.Tickets.Mappers;

import com.miguel.raffles.Tickets.DTO.RaffleTicketsResponse;
import com.miguel.raffles.Tickets.Ticket;
import org.springframework.stereotype.Service;

@Service
public class RaffleTicketsResponseMapper {

    public RaffleTicketsResponse fromTicket(Ticket ticket) {
        return RaffleTicketsResponse.builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .status(ticket.getStatus())
                .customerId(ticket.getCustomerId())
                .build();
    }
}
