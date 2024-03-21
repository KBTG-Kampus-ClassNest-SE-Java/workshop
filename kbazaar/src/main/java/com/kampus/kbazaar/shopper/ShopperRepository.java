package com.kampus.kbazaar.shopper;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Integer> {
    Optional<Shopper> findByUsername(String username);
}
