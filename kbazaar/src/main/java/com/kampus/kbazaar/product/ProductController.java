package com.kampus.kbazaar.product;

import com.kampus.kbazaar.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiResponse(
            responseCode = "200",
            description = "list all products",
            content = {
                @Content(
                        mediaType = "application/json",
                        array =
                                @ArraySchema(
                                        schema = @Schema(implementation = ProductResponse.class)))
            })
    @ApiResponse(
            responseCode = "500",
            description = "internal server error",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundException.class)))
    @GetMapping("/products")
    public List<ProductResponse> getProducts() {
        return productService.getAll();
    }

    @ApiResponse(
            responseCode = "200",
            description = "get product by sku",
            content = {
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ProductResponse.class))
            })
    @ApiResponse(
            responseCode = "404",
            description = "product not found",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundException.class)))
    @GetMapping("/products/{sku}")
    public ProductResponse getProductById(@PathVariable String sku) {
        return productService.getBySku(sku);
    }
}
