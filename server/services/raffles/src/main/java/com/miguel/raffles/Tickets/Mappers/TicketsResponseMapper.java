package com.miguel.raffles.Tickets.Mappers;

import com.miguel.raffles.Raffles.RafflesMapper;
import com.miguel.raffles.Tickets.DTO.PurchaseTicketResponse;
import com.miguel.raffles.Tickets.Ticket;
import com.miguel.raffles.Tickets.DTO.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketsResponseMapper {


    private final RafflesMapper rafflesMapper;

    public TicketResponse fromTicketToTicketResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .raffle(
                        rafflesMapper.fromRaffleToTicketRaffleResponse(
                                ticket.getRaffle()
                        )
                )
                .ticketNumber(ticket.getTicketNumber())
                .status(ticket.getStatus())
                .customerId(ticket.getCustomerId())
                .build();
    }
}
