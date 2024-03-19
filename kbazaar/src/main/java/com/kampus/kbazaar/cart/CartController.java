package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CartController {
    public static Cart[] carts = new Cart[]{
            new Cart(1, new Product[]{
                    new Product("sku1", "product1", 100, 1),
            }),
            new Cart(2, new Product[]{
                    new Product("sku2", "product2", 200, 2),
            }),
            new Cart(3, new Product[]{
                    new Product("sku3", "product3", 300, 3),
            }),
    };


    @PostMapping("/carts/{id}/additems")
    public ResponseEntity addProductToCart(@PathVariable String id, @RequestBody Product[] products) {
        Cart cart = new Cart(1, new Product[]{
                new Product("sku1", "product1", 100, 1),
        });

        return ResponseEntity.ok(cart);
    }

}
