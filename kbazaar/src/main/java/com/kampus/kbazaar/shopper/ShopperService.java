package com.kampus.kbazaar.shopper;

import com.kampus.kbazaar.exceptions.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShopperService {

    private ShopperRepository shopperRepository;

    public ShopperService(ShopperRepository shopperRepository) {
        this.shopperRepository = shopperRepository;
    }

    public List<ShopperResponse> getAll() {
        return shopperRepository.findAll().stream().map(Shopper::toResponse).toList();
    }

    public ShopperResponse getById(String id) {
        return shopperRepository
                .findById(Integer.parseInt(id))
                .map(Shopper::toResponse)
                .orElseThrow(() -> new NotFoundException("Shopper not found"));
    }

    public ShopperResponse getByUsername(String username) {
        return shopperRepository
                .findByUsername(username)
                .map(Shopper::toResponse)
                .orElseThrow(() -> new NotFoundException("Shopper not found"));
    }
}
