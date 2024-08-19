package com.miguel.raffles.Tickets.DTO;

import java.util.Set;

public record PurchaseRequest(
        Set<Integer> ticketsIds,

        Integer customerId
) {
}
