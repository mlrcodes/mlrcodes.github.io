package com.miguel.raffles.Tickets;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient(
        name = "raffles-service",
        url = "${application.config.tickets-url}"
)
public interface TicketsClient {
    @PutMapping("/reserve")
    public ResponseEntity<Void> reserve(
            @RequestBody Set<Integer> ticketsIds
    );
}
