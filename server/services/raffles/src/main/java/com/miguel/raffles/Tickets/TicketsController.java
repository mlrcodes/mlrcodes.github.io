package com.miguel.raffles.Tickets;

import com.miguel.raffles.Tickets.DTO.PurchaseRequest;
import com.miguel.raffles.Tickets.DTO.PurchaseTicketResponse;
import com.miguel.raffles.Tickets.DTO.TicketResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketsController {

    private final TicketsService service;

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> findById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PutMapping("/purchase")
    public ResponseEntity<Set<PurchaseTicketResponse>> purchase(
            @RequestBody @Valid PurchaseRequest purchaseRequest
    ) {
        this.service.purchase(purchaseRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reserve")
    public ResponseEntity<Void> reserve(
            @RequestBody Set<Integer> ticketsIds
    ) {
        this.service.reserve(ticketsIds);
        return ResponseEntity.ok().build();
    }


}
