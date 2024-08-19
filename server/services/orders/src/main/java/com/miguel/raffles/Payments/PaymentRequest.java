package com.miguel.raffles.Payments;

import com.miguel.raffles.Customers.CustomerDTO;
import com.miguel.raffles.Orders.DTO.OrderTicketDTO;
import lombok.Builder;

import java.util.Set;

@Builder
public record PaymentRequest(

        Integer orderId,

        Integer raffleId,

        String paymentMethod,

        CustomerDTO customer,

        Integer ticketsQuantity
) {
}
