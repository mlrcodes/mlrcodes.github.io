package com.miguel.raffles.Stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

@Service
public class StripeService {


    public PaymentIntent createPaymentIntent(Long amount) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setCurrency("eur")
                .setAmount(amount)
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return paymentIntent;
    }

    public PaymentIntent updateStripePayment(String stripeCustomerId, String paymentMethod, Long purchaseAmount) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(purchaseAmount)
                .setCurrency("eur")
                .setCustomer(stripeCustomerId)
                .setPaymentMethod(paymentMethod)
                .build();

        return PaymentIntent.create(params);
    }

    public String handlePaymentResult(String payload, String sigHeader) {
        String response = "";

        Dotenv dotenv = Dotenv.load();
        String endpointSecret = dotenv.get("STRIPE_WEBHOOK_SECRET");

        try {
            Event event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret
            );

            switch (event.getType()) {
                case "payment_intent.succeeded":
                    response = "Pago exitoso";
                    break;
                case "payment_intent.payment_failed":
                    response = "Pago fallido";
                    break;
            }

        } catch (Exception e) {
            return "Error";
        }
        return response;
    }

    public String getPublicKey() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("STRIPE_PUBLIC_KEY");
    }

    public void confirmPayment(String paymentId) throws StripeException{
        Dotenv dotenv = Dotenv.load();
        Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);
        paymentIntent.confirm();
    }
}
