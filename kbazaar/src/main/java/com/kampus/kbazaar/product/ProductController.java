package com.kampus.kbazaar.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    protected static final Product[] productsDB =
            new Product[] {
                new Product("sku1", "Product 1", 100.0, 10),
                new Product("sku2", "Product 2", 200.0, 20),
                new Product("sku3", "Product 3", 300.0, 30)
            };

    @GetMapping("/products")
    public ResponseEntity<Product[]> getProducts() {
        return ResponseEntity.ok(productsDB);
    }

    @GetMapping("/products/{sku}")
    public ResponseEntity<Product> getProductById(@PathVariable String sku) {
        for (Product product : productsDB) {
            if (product.getSku().equals(sku)) {
                return ResponseEntity.ok(product);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
