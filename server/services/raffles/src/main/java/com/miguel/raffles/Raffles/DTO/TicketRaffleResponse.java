package com.miguel.raffles.Raffles.DTO;

import com.miguel.raffles.Raffles.RaffleStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record TicketRaffleResponse(

        Integer id,

        String title,

        String description,

        LocalDateTime startDate,

        LocalDateTime endDate,

        RaffleStatus status,

        Set<String> photoUrls,

        Integer associationId
) {
}
