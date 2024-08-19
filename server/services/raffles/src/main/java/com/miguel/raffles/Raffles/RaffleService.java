package com.miguel.raffles.Raffles;

import com.miguel.raffles.Associations.AssociationResponse;
import com.miguel.raffles.Associations.AssociationsClient;
import com.miguel.raffles.Exceptions.CustomExceptions.BusinessException;
import com.miguel.raffles.Raffles.DTO.RaffleCreationRequest;
import com.miguel.raffles.Raffles.DTO.RaffleResponse;
import com.miguel.raffles.Tickets.TicketsService;
import com.miguel.raffles.Exceptions.CustomExceptions.RaffleNotFoundException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.ProductUpdateParams;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RaffleService {

    private final RafflesRepository repository;

    private final RafflesMapper mapper;

    private final TicketsService ticketsService;

    private final AssociationsClient associationsClient;

    @Transactional
    public Integer createRaffle(RaffleCreationRequest request) throws StripeException {

        associationsClient.findById(request.associationId())
                .orElseThrow(() -> new BusinessException("Association not found"));

        Raffle raffle = mapper.toRaffle(request);

        String stripeProductId = this.createStripeProduct(request);

        raffle.setStripeProductId(stripeProductId);

        raffle.setTickets(
                ticketsService.createTickets(request.ticketsInfo().amount(), raffle)
        );

        Raffle savedRaffle = repository.save(raffle);
        return savedRaffle.getId();
    }

    private String createStripeProduct(RaffleCreationRequest request) throws StripeException {
        Dotenv dotenv = Dotenv.load();
        Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");

        ProductCreateParams params = ProductCreateParams.builder()
                .setName("Ticket for raffle '" + request.title() + "' in association " + request.associationId())
                .build();

        Product stripeProduct = Product.create(params);

        String stripeProductId = stripeProduct.getId();

        String stripePriceId = this.createStripePrice(request.ticketsInfo().price(), stripeProductId);

        ProductUpdateParams updateParams = ProductUpdateParams.builder()
                .setDefaultPrice(stripePriceId)
                .build();

        stripeProduct.update(updateParams);

        return stripeProductId;
    }

    private String createStripePrice(Double price, String stripeProductId) throws StripeException {
        Dotenv dotenv = Dotenv.load();
        Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");

        PriceCreateParams params = PriceCreateParams.builder()
                .setCurrency("eur")
                .setUnitAmount(Math.round(price * 100))
                .setProduct(stripeProductId)
                .build();

        Price stripePrice = Price.create(params);

        return stripePrice.getId();
    }

    public RaffleResponse getById(Integer id) {
        return mapper.fromRaffleToRaffleResponse(
                repository.findById(id)
                        .orElseThrow(() -> new RaffleNotFoundException("Raffle not found"))
        );
    }

    public String getStripeProductIdById(Integer id) {
        return this.repository.getStripeProductIdById(id);
    }
}
