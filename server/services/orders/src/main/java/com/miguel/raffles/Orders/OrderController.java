package com.miguel.raffles.Orders;

import com.miguel.raffles.Orders.DTO.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrdersService service;

    @PostMapping
    public ResponseEntity<String> reserve(
            @RequestBody @Valid OrderRequest request
    ) {
        return ResponseEntity.ok(service.reserve(request));
    }

}
