package com.miguel.raffles.Customers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "customers-service",
        url = "${application.config.customers-url}"
)
public interface CustomerClient {
    @PostMapping
    CustomerDTO createCustomer(@RequestBody CustomerOrderInfo request);
}
