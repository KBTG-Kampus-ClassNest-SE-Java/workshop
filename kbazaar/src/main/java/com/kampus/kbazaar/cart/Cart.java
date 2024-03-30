package com.kampus.kbazaar.cart;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Description("discount on cart")
    @Column(name = "discount")
    private BigDecimal discount;

    @Description("total discount product in cart")
    @Column(name = "total_discount")
    private BigDecimal totalDiscount;

    @Column(name = "promotion_codes")
    private String promotionCodes;

    @Description("price total product in cart")
    @Column(name = "sub_total")
    private BigDecimal subtotal;

    @Description("price total after discount all")
    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public CartResponse toCartResponse() {
        val dto = new CartResponse();

        dto.setUsername(this.getUsername());
        dto.setPromotionCodes(this.getPromotionCodes());
        dto.setDiscount(this.getDiscount());
        dto.setTotalDiscount(this.getTotalDiscount());
        dto.setSubtotal(this.getSubtotal());
        dto.setGrandTotal(this.getGrandTotal());

        return dto;
    }
}
