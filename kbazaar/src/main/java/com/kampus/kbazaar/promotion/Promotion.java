package com.kampus.kbazaar.promotion;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "promotion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long promotionId;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "discount_type", nullable = false)
    private String discountType;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "max_discount_amount")
    private BigDecimal maxDiscountAmount;

    @Column(name = "applicable_to", nullable = false)
    private String applicableTo;

    @Column(name = "product_skus")
    private String productSkus;

    @Column(name = "min_quantity")
    private Integer minQuantity;

    @Column(name = "free_quantity")
    private Integer freeQuantity;

    public PromotionResponse toResponse() {
        return new PromotionResponse(
                this.promotionId,
                this.code,
                this.name,
                this.description,
                this.startDate,
                this.endDate,
                this.discountType,
                this.discountAmount,
                this.maxDiscountAmount,
                this.applicableTo,
                Optional.ofNullable(this.productSkus)
                        .map(
                                s ->
                                        Arrays.stream(s.split(","))
                                                .filter(str -> !str.isEmpty())
                                                .toList())
                        .orElse(Collections.emptyList()),
                this.minQuantity,
                this.freeQuantity);
    }
}
