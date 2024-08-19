package com.miguel.raffles.Raffles;

import com.miguel.raffles.Raffles.DTO.RaffleCreationRequest;
import com.miguel.raffles.Raffles.DTO.RaffleResponse;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/raffles")
public class RaffleController {

    private final RaffleService service;

    @PostMapping
    public ResponseEntity<Integer> createRaffle(
            @RequestBody @Valid RaffleCreationRequest request
    ) throws StripeException {
        return ResponseEntity.ok(service.createRaffle(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaffleResponse> finById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/stripe-product/{id}")
    public ResponseEntity<String> getStripeProductIdById(
            @PathVariable Integer id
    )  {
        return ResponseEntity.ok(this.service.getStripeProductIdById(id));
    }
}
