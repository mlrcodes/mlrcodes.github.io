package com.miguel.raffles.Raffles;

import com.miguel.raffles.Raffles.DTO.RaffleCreationRequest;
import com.miguel.raffles.Raffles.DTO.RaffleResponse;
import com.miguel.raffles.Raffles.DTO.TicketRaffleResponse;
import com.miguel.raffles.Tickets.DTO.RaffleTicketsResponse;
import com.miguel.raffles.Tickets.Mappers.RaffleTicketsResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RafflesMapper {

    private final RaffleTicketsResponseMapper ticketsMapper;

    public Raffle toRaffle(RaffleCreationRequest request) {
        return Raffle.builder()
                .title(request.title())
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .photoUrls(request.photoUrls())
                .associationId(request.associationId())
                .photoUrls(request.photoUrls())
                .build();
    }

    public RaffleResponse fromRaffleToRaffleResponse(Raffle raffle) {

        Set<RaffleTicketsResponse> tickets = raffle.getTickets()
                .stream()
                .map(ticketsMapper::fromTicket)
                .collect(Collectors.toSet());

        return RaffleResponse.builder()
                .id(raffle.getId())
                .title(raffle.getTitle())
                .description(raffle.getDescription())
                .tickets(tickets)
                .startDate(raffle.getStartDate())
                .endDate(raffle.getEndDate())
                .associationId(raffle.getAssociationId())
                .photoUrls(raffle.getPhotoUrls())
                .build();
    }

    public TicketRaffleResponse fromRaffleToTicketRaffleResponse(Raffle raffle) {
        return TicketRaffleResponse.builder()
                .id(raffle.getId())
                .title(raffle.getTitle())
                .description(raffle.getDescription())
                .startDate(raffle.getStartDate())
                .endDate(raffle.getEndDate())
                .associationId(raffle.getAssociationId())
                .photoUrls(raffle.getPhotoUrls())
                .build();
    }
}
