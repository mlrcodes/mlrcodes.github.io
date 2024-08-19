package com.miguel.raffles.Raffles;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "raffles-service",
        url = "${application.config.raffles-url}"
)
public interface RafflesClient {

    @GetMapping("/stripe-product/{id}")
    String getStripeProductIdById(@PathVariable Integer id);
}
