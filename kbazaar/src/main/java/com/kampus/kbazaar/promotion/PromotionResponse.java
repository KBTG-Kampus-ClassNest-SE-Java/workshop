package com.kampus.kbazaar.promotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PromotionResponse(
        Long promotionId,
        String code,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String discountType,
        BigDecimal discountAmount,
        BigDecimal maxDiscountAmount,
        String applicableTo,
        List<String> productSkus,
        Integer minQuantity,
        Integer freeQuantity) {}
