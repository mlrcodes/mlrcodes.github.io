package com.miguel.raffles.Orders.DTO;

import com.miguel.raffles.Customers.CustomerOrderInfo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OrderRequest (

        @NotNull(message = "Should select a raffle")
        Integer raffle,

        @NotEmpty(message = "Should purchase at least one product")
        Set<Integer> tickets
) {
}
