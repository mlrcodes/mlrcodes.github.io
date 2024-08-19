package com.miguel.raffles.Customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c.stripeId FROM Customer c WHERE c.id = :id")
    String getStripeIdById(Integer id);
}
