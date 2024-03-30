package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.PromotionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    @GetMapping("/carts")
    public ResponseEntity getCart() { // NOSONAR
        return ResponseEntity.ok().build();
    }

    private CartService cartService;

    @PostMapping("/carts/{username}/promotions")
    public ResponseEntity<Cart> applyPromotionCode(
            @PathVariable String username,
            @RequestBody PromotionRequest promotionRequest
            ) {
        Cart updatedCart = cartService.applyPromotionCode(username, promotionRequest);
        return ResponseEntity.ok(updatedCart);
    }

}
