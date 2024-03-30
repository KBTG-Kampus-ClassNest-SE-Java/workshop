package com.kampus.kbazaar.cart;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public List<CartResponse> getCart() {
        return cartService.getCarts();
    }

    @GetMapping("/carts/v2")
    public List<CartResponse> getCartsV2() {
        return cartService.getCartsV2();
    }

    @GetMapping("/carts/v3")
    public List<CartResponse> getCartsV3() {
        return cartService.getCartsV3();
    }
}
