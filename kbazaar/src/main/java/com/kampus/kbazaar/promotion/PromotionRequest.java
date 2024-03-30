package com.kampus.kbazaar.promotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PromotionRequest {
    private String code;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String discountType;
    private BigDecimal discountAmount;
    private String applicableTo;
    private List<String> productSkus;
    public List<String> getProductSkus() {
        return productSkus;
    }
    public void setProductSkus(List<String> productSkus) {
        this.productSkus = productSkus;
    }
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
