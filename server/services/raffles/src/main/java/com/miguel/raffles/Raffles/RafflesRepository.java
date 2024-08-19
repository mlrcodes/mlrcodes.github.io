package com.miguel.raffles.Raffles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RafflesRepository extends JpaRepository<Raffle, Integer> {
    @Query("SELECT r.stripeProductId FROM Raffle r WHERE id = :id")
    String getStripeProductIdById(Integer id);
}
