package com.kampus.kbazaar.shopper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Integer> {
    Optional<Shopper> findByUsername(String username);
}
