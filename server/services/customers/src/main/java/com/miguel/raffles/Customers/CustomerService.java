package com.miguel.raffles.Customers;

import com.miguel.raffles.Customers.DTO.CustomerRequest;
import com.miguel.raffles.Customers.DTO.CustomerResponse;
import com.miguel.raffles.Exceptions.CustomExceptions.CustomerNotFoundException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.CustomerCreateParams;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponse createCustomer(CustomerRequest request) throws StripeException {

        com.stripe.model.Customer customer = this.createStripeCustomer(request);

        Customer localCustomer = this.repository.save(
                Customer.builder()
                        .stripeId(customer.getId())
                        .build()
        );

        return CustomerResponse.builder()
                .id(localCustomer.getId())
                .stripeId(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhone())
                .build();
    }

    private com.stripe.model.Customer createStripeCustomer(CustomerRequest request) throws StripeException {
        Dotenv dotenv = Dotenv.load();
        Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");

        CustomerCreateParams params =
                CustomerCreateParams.builder()
                        .setName(request.firstname() + " " + request.lastname())
                        .setEmail(request.email())
                        .setPhone(request.phoneNumber())
                        .build();

        com.stripe.model.Customer customer = com.stripe.model.Customer.create(params);

        return customer;
    }

    public String getStripeIdById(Integer id) {
        return this.repository.getStripeIdById(id);
    }
}
