package com.miguel.raffles.Customers;

import com.miguel.raffles.Customers.DTO.CustomerRequest;
import com.miguel.raffles.Customers.DTO.CustomerResponse;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer (
            @RequestBody @Valid CustomerRequest request
    ) throws StripeException {
        return ResponseEntity.ok(this.service.createCustomer(request));
    }

    @GetMapping("/stripe-id/{id}")
    public ResponseEntity<String> getStripeIdById(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(service.getStripeIdById(id));
    }
}
