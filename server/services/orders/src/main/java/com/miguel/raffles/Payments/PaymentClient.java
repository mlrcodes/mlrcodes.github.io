package com.miguel.raffles.Payments;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient (
        name = "payment-client",
        url = "${application.config.payments-url}"
)
public interface PaymentClient {
    @PostMapping("/reservation-payment")
    String createReservationPayment(
            @RequestBody @Valid ReservationRequest request
    );

}
