package com.miguel.raffles.Associations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationsRepository extends JpaRepository<Association, Integer> {
}
