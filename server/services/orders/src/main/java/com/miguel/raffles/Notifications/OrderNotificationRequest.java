package com.miguel.raffles.Notifications;


import com.miguel.raffles.Customers.CustomerDTO;
import com.miguel.raffles.Orders.DTO.OrderTicketDTO;
import com.miguel.raffles.Payments.PaymentResponse;
import lombok.Builder;

import java.util.Set;

@Builder
public record OrderNotificationRequest(

        Integer orderId,
        String orderReference,

        CustomerDTO customer,

        PaymentResponse paymentData,

        Set<OrderTicketDTO> tickets
) {
}
