package com.kampus.kbazaar.promotion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.kampus.kbazaar.exceptions.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PromotionServiceTest {

    @Mock private PromotionRepository promotionRepository;

    @InjectMocks private PromotionService promotionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should be able to get all promotions")
    void shouldBeAbleToGetAllPromotion() {
        // Arrange
        Promotion promotion1 = new Promotion();
        Promotion promotion2 = new Promotion();
        when(promotionRepository.findAll()).thenReturn(Arrays.asList(promotion1, promotion2));

        // Act
        List<PromotionResponse> promotionResponses = promotionService.getAll();

        // Assert
        assertEquals(2, promotionResponses.size());
    }

    @Test
    @DisplayName("should be able to get promotion by code")
    void getPromotionByCode_ExistingCode_ShouldReturnPromotionResponse() {
        // Arrange
        String code = "BUY2GET1FREE";
        Promotion promotion = new Promotion();
        promotion.setCode(code);
        when(promotionRepository.findByCode(code)).thenReturn(Optional.of(promotion));

        // Act
        PromotionResponse promotionResponse = promotionService.getPromotionByCode(code);

        // Assert
        assertNotNull(promotionResponse);
        assertEquals(code, promotionResponse.code());
    }

    @Test
    @DisplayName("should throw NotFoundException when promotion code not found")
    void getPromotionByCode_NonExistingCode_ShouldThrowNotFoundException() {
        // Arrange
        String code = "NON-EXISTING-CODE";
        when(promotionRepository.findByCode(code)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> promotionService.getPromotionByCode(code));
    }
}
