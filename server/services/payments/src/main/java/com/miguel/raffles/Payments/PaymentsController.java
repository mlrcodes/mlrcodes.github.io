package com.miguel.raffles.Payments;

import com.miguel.raffles.Payments.DTO.PaymentRequest;
import com.miguel.raffles.Payments.DTO.ReservationRequest;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "*")
public class PaymentsController {

    private final PaymentsService service;


    @PostMapping("/reservation-payment")
    public ResponseEntity<String> createReservationPayment(
            @RequestBody @Valid ReservationRequest request
    ) throws StripeException {
        return ResponseEntity.ok(service.createReservationPayment(request));
    }

    @PostMapping("/purchase-payment")
    public ResponseEntity<String> processPayment(
            @RequestBody @Valid PaymentRequest request
    ) throws StripeException {
        return ResponseEntity.ok(service.processPayment(request));
    }
}
