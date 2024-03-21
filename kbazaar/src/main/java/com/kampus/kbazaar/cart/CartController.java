package com.kampus.kbazaar.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    @GetMapping("/carts")
    public ResponseEntity getCart() {
        return ResponseEntity.ok().build();
    }
}
