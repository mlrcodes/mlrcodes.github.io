package com.miguel.raffles.Raffles.DTO;

import com.miguel.raffles.Raffles.RaffleStatus;
import com.miguel.raffles.Tickets.DTO.RaffleTicketsResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record RaffleResponse(

        Integer id,

        String title,

        String description,

        LocalDateTime startDate,

        LocalDateTime endDate,

        RaffleStatus status,

        Set<String> photoUrls,

        Set<RaffleTicketsResponse> tickets,

        Integer associationId
) {
}
