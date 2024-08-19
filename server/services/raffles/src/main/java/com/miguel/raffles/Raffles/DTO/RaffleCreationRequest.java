package com.miguel.raffles.Raffles.DTO;


import com.miguel.raffles.Raffles.RaffleStatus;
import com.miguel.raffles.Tickets.DTO.RaffleTicketsCreationRequest;

import java.time.LocalDateTime;
import java.util.Set;

public record RaffleCreationRequest(

        String title,

        String description,

        LocalDateTime startDate,

        LocalDateTime endDate,

        RaffleStatus status,

        Set<String> photoUrls,

        RaffleTicketsCreationRequest ticketsInfo,

        Integer associationId

) {
}
