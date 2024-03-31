package com.kampus.kbazaar.cart;

import java.math.BigDecimal;

public class CartItemResponse {
    private String sku;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal discount;
    private String promotionCodes;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getPromotionCodes() {
        return promotionCodes;
    }

    public void setPromotionCodes(String promotionCodes) {
        this.promotionCodes = promotionCodes;
    }
}
