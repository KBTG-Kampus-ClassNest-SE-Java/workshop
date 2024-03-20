package com.kampus.kbazaar.product;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    protected static final Product[] productsDB =
            new Product[] {
                new Product(1L, "Product 1", "sku-1", 100.0, 10),
            };

    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "list all products",
                content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                }),
        @ApiResponse(
                responseCode = "500",
                description = "internal server error",
                content =
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/products")
    public ResponseEntity<Product[]> getProducts() {
        List<Product> pros = productRepository.findAll();

        System.out.println("Products:");
        System.out.println(pros.size());
        for (Product product : pros) {
            System.out.println(product);
        }
        System.out.println(":done");

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
