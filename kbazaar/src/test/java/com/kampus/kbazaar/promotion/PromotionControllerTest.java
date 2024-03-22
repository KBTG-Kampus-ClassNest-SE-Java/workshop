package com.kampus.kbazaar.promotion;

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
        controllers = PromotionController.class,
        excludeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = JwtAuthFilter.class))
public class PromotionControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private PromotionService promotionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should return all promotions")
    public void shouldReturnAllPromotions() throws Exception {
        // Given

        // When & Then
        when(promotionService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/promotions").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(promotionService, times(1)).getAll();
    }

    @Test
    @DisplayName("should return promotion")
    public void shouldReturnPromotion() throws Exception {
        // Given
        String code = "PROMO-1";

        // When & Then
        when(promotionService.getPromotionByCode(code)).thenReturn(null);

        mockMvc.perform(get("/api/v1/promotions/" + code).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(promotionService, times(1)).getPromotionByCode(code);
    }
}
