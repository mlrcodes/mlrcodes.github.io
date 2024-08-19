package com.miguel.raffles.Orders;

import com.miguel.raffles.Exceptions.CustomExceptions.PaymentProcessException;
import com.miguel.raffles.Exceptions.CustomExceptions.TicketPurchaseException;
import com.miguel.raffles.Orders.DTO.*;
import com.miguel.raffles.Payments.*;
import com.miguel.raffles.Tickets.TicketsClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrdersService {

    private final OrderRepository repository;

    private final TicketsClient ticketsClient;

    private final PaymentClient paymentClient;

    @Transactional
    public String reserve(OrderRequest request) {

        // Reserve tickets
        try {
            this.ticketsClient.reserve(request.tickets());
        } catch (RuntimeException exp) {
            throw new TicketPurchaseException("Tickets could not be purchased: " + exp.getMessage());
        }

        // Build and save order
        Order order = repository.save(
                Order.builder()
                .ticketsIds(request.tickets())
                .build()
        );

        String clientSecret;
        try {
            clientSecret = paymentClient.createReservationPayment(
                    ReservationRequest.builder()
                            .orderId(order.getId())
                            .raffleId(request.raffle())
                            .ticketsQuantity(request.tickets().size())
                            .build()
            );
        } catch (RuntimeException ex) {
            throw new PaymentProcessException("Error processing payment: " + ex.getMessage());
        }

        return clientSecret;
    }
}
