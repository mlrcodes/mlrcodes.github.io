package com.miguel.raffles.Stripe;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stripe")
public class StripeController {

    private final StripeService service;

    @GetMapping("/public-key")
    public ResponseEntity<String> getPublicKey() {
        return ResponseEntity.ok(this.service.getPublicKey());
    }

    @PostMapping("/confirm-payment")
    public void confirmPayment(
            String paymentId
    ) throws StripeException {
        this.service.confirmPayment(paymentId);
    }
    @PostMapping("/webhook")
    public ResponseEntity<String> handlePaymentResult(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader
    ) throws StripeException {
        return ResponseEntity.ok(service.handlePaymentResult(payload, sigHeader));
    }



}
