package com.kampus.kbazaar.shopper;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ShopperController {

    private ShopperService shopperService;

    public ShopperController(ShopperService shopperService) {
        this.shopperService = shopperService;
    }

    @GetMapping("/shopper")
    public List<ShopperResponse> getAllUsers() {
        return shopperService.getAll();
    }

    @GetMapping("/shopper/{id}")
    public ShopperResponse getUserById(@PathVariable String id) {
        return shopperService.getById(id);
    }
}
