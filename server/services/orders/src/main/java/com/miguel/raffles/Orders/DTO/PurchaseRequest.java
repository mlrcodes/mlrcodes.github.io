package com.miguel.raffles.Orders.DTO;

import lombok.Builder;

import java.util.Set;

@Builder
public record PurchaseRequest(
        Set<Integer> ticketsIds,
        Integer customerId
){
}
