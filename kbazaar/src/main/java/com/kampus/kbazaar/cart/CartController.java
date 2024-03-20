package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {
    protected static final Cart[] carts =
            new Cart[] {
                new Cart(1, new Product[] {new Product(11L, "Product 1", "sku-1", 100.0, 10)}),
                new Cart(
                        2,
                        new Product[] {
                            new Product(22L, "Product 2", "sku-2", 200.0, 2),
                        }),
                new Cart(
                        3,
                        new Product[] {
                            new Product(33L, "Product 3", "sku-3", 300.0, 3),
                        }),
            };

    @PostMapping("/carts/{id}/additems")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable String id, @RequestBody Product[] products) {
        Cart cart =
                new Cart(
                        1,
                        new Product[] {
                            new Product(11L, "Product 1", "sku-1", 100.0, 10),
                            new Product(22L, "Product 2", "sku-2", 200.0, 2),
                            new Product(33L, "Product 3", "sku-3", 300.0, 3),
                        });

        return ResponseEntity.ok(cart);
    }
}
