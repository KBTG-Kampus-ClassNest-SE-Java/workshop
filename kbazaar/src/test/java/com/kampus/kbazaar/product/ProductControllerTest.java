package com.kampus.kbazaar.product;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kampus.kbazaar.security.JwtAuthFilter;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(
        controllers = ProductController.class,
        excludeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = JwtAuthFilter.class))
public class ProductControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should return all product")
    public void shouldReturnAllProduct() throws Exception {
        // Given

        // When & Then
        when(productService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).getAll();
    }

    @Test
    @DisplayName("should return product")
    public void shouldReturnProduct() throws Exception {
        // Given
        String sku = "PROMO-1";

        // When & Then
        when(productService.getBySku(sku)).thenReturn(null);

        mockMvc.perform(get("/api/v1/products/" + sku).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).getBySku(sku);
    }
}
