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
        return shopperRepository.findAll().stream().map(s -> s.toResponse()).toList();
    }

    public ShopperResponse getById(String id) {
        return shopperRepository
                .findById(Integer.parseInt(id))
                .map(s -> s.toResponse())
                .orElseThrow(() -> new NotFoundException("Shopper not found"));
    }
}
