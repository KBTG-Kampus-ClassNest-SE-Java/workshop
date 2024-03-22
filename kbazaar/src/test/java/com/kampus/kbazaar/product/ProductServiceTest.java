package com.kampus.kbazaar.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.kampus.kbazaar.exceptions.NotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductServiceTest {

    @Mock private ProductRepository productRepository;

    @InjectMocks private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should be able to get all products")
    void shouldBeAbleToGetAllProducts() {
        // Mock data
        Product product1 =
                new Product(
                        1L,
                        "Google Pixel 5",
                        "MOBILE-GOOGLE-PIXEL-5",
                        new BigDecimal(12990.75),
                        100);
        Product product2 =
                new Product(2L, "Coca-Cola", "BEV-COCA-COLA", new BigDecimal(20.75), 150);
        List<Product> productList = Arrays.asList(product1, product2);

        // Mock repository method
        when(productRepository.findAll()).thenReturn(productList);

        // Call service method
        List<ProductResponse> result = productService.getAll();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Google Pixel 5", result.get(0).name());
        assertEquals("BEV-COCA-COLA", result.get(1).sku());
    }

    @Test
    @DisplayName("should return empty list when no product found")
    void shouldReturnEmptyListWhenNoProductFoundGetAllProducts() {
        // Mock repository method returning empty list
        when(productRepository.findAll()).thenReturn(Arrays.asList());

        // Call service method
        List<ProductResponse> result = productService.getAll();

        // Assertions
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("should be able to get product by SKU")
    void shouldBeAbleToGetProductBySku() {
        // Mock data
        Product product =
                new Product(1L, "Pens", "STATIONERY-PEN-BIC-BALLPOINT", new BigDecimal(14.99), 100);

        // Mock repository method
        when(productRepository.findBySku("STATIONERY-PEN-BIC-BALLPOINT"))
                .thenReturn(Optional.of(product));

        // Call service method
        ProductResponse result = productService.getBySku("STATIONERY-PEN-BIC-BALLPOINT");

        // Assertions
        assertEquals("Pens", result.name());
        assertEquals(new BigDecimal(14.99), result.price());
    }

    @Test
    @DisplayName("should return null when get product non-existing SKU")
    void shouldReturnNullWhenGetProductNonExistingSKU() {
        // Mock repository method returning empty optional
        when(productRepository.findBySku(anyString())).thenReturn(Optional.empty());

        // Assertions
        assertThrows(NotFoundException.class, () -> productService.getBySku("NonExistingSKU"));
    }
}
