package com.miguel.raffles.Tickets;

import com.miguel.raffles.Exceptions.CustomExceptions.BusinessException;
import com.miguel.raffles.Exceptions.CustomExceptions.TicketNotFoundException;
import com.miguel.raffles.Raffles.Raffle;
import com.miguel.raffles.Tickets.DTO.*;
import com.miguel.raffles.Tickets.Mappers.TicketsResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.miguel.raffles.Tickets.TicketStatus.*;

@RequiredArgsConstructor
@Service
public class TicketsService {

    private final TicketsResponseMapper mapper;

    private final TicketsRepository repository;

    public Set<Ticket> createTickets(Integer amount, Raffle raffle) {
        Set<Ticket> tickets = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            tickets.add(
                    Ticket.builder()
                            .raffle(raffle)
                            .status(AVAILABLE)
                            .ticketNumber(i + 1L)
                            .build()
            );
        }
        return tickets;
    }

    public TicketResponse findById(Integer id) {
        return mapper.fromTicketToTicketResponse(
                repository.findById(id)
                        .orElseThrow(() -> new TicketNotFoundException("Ticket not found"))
        );
    }

    public void purchase(PurchaseRequest request) {
        List<Ticket> tickets = this.repository.findAllById(request.ticketsIds());

        if (tickets.isEmpty() || (tickets.size() != request.ticketsIds().size())) {
            throw new TicketNotFoundException("One ore more tickets were not found");
        }

        // check availability
        tickets.forEach(ticket -> {
            if (ticket.getStatus() != RESERVED)
                throw new BusinessException("One or more tickets are already sold");
        });

        // change customerId and status
        tickets.forEach(ticket -> {
            ticket.setStatus(SOLD);
            ticket.setCustomerId(request.customerId());
            this.repository.save(ticket);
        });
    }

    public void reserve(Set<Integer> ticketsIds) {
        List<Ticket> tickets = this.repository.findAllById(ticketsIds);

        if (tickets.isEmpty() || (tickets.size() != ticketsIds.size())) {
            throw new TicketNotFoundException("One ore more tickets were not found");
        }

        // check availability
        tickets.forEach(ticket -> {
            if (ticket.getStatus() != AVAILABLE)
                throw new BusinessException("One or more tickets are already sold");
        });

        // change customerId and sold attributes,
        tickets.forEach(ticket -> {
            ticket.setStatus(RESERVED);
            this.repository.save(ticket);
        });
    }

}
