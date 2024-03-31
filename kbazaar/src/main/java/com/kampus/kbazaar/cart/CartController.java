package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.Promotion;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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
        List<Cart> carts = Optional.of(cartService.getAllCarts()).orElse(List.of());
        if (carts.isEmpty()) {
            return List.of();
        }

        return carts.stream()
                .map(cart -> cart.toResponse(cartService.getCartItems(cart.getUsername())))
                .toList();
    }

    @PostMapping("/carts/{username}/items")
    public CartResponse addItemToCart(@PathVariable String username, @RequestBody CartItem item) {
        item.setUsername(username);
        List<CartItem> items = cartService.addItemToCart(username, item);
        BigDecimal subtotal = cartService.sumOfSubtotalPrice(items);
        Cart cart = cartService.createOrUpdateCart(username, subtotal);
        return cart.toResponse(items);
    }

    @PostMapping("/carts/{username}/promotions")
    public CartResponse applyPromotion(
            @PathVariable String username, @RequestBody Promotion promotion) {
        if (promotion.getApplicableTo().equals("SPECIFIC_PRODUCTS")) {
            List<CartItem> items = cartService.applyPromotionToItem(username, promotion);
            BigDecimal subtotal = cartService.sumOfSubtotalPrice(items);
            BigDecimal itemDiscount = cartService.sumOfItemsDiscount(items);
            Cart cart = cartService.updateDiscountItemToCart(username, itemDiscount, subtotal);
            return cart.toResponse(items);
        }

        if (promotion.getApplicableTo().equals("ENTIRE_CART")) {
            Cart cart = cartService.applyPromotionToCart(username, promotion);
            List<CartItem> items = cartService.getCartItems(username);
            return cart.toResponse(items);
        }

        throw new RuntimeException("Not implemented yet");
    }
}
