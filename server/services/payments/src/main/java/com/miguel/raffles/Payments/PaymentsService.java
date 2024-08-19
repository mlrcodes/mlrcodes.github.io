package com.miguel.raffles.Payments;

import com.miguel.raffles.Payments.DTO.PaymentRequest;
import com.miguel.raffles.Payments.DTO.ReservationRequest;
import com.miguel.raffles.Raffles.RafflesClient;
import com.miguel.raffles.Stripe.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Price;
import com.stripe.model.Product;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentsService {

    private final PaymentsRepository repository;

    private final RafflesClient rafflesClient;

    private final StripeService stripeService;


    public String processPayment(PaymentRequest request) throws StripeException {
        return "";
    }


    public String createReservationPayment(ReservationRequest request) throws StripeException {
        Long amount = this.calculatePurchaseAmount(request.raffleId(), request.ticketsQuantity());

        PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount);

        this.repository.save(
                Payment.builder()
                        .stripePaymentId(paymentIntent.getId())
                        .orderId(request.orderId())
                        .build()
        );

        return paymentIntent.getClientSecret();
    }

    private Long calculatePurchaseAmount(Integer raffleId, Integer ticketsQuantity) throws StripeException {
        String stripeProductId = this.rafflesClient.getStripeProductIdById(raffleId);

        Dotenv dotenv = Dotenv.load();
        Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");

        Product product = Product.retrieve(stripeProductId);

        String priceId = product.getDefaultPrice();

        Price price = Price.retrieve(priceId);

        Long productPrice = price.getUnitAmount();

        return ticketsQuantity * productPrice;
    }
}
