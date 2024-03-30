package com.kampus.kbazaar.cart;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "total_discount")
    private BigDecimal totalDiscount;

    @Column(name = "promotion_codes")
    private String promotionCodes = "";

    @Description("precisely reflect its pre-discount status")
    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Description("the final, all-inclusive amount to be paid.")
    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    public static Cart empty(String username) {
        return new Cart(
                null,
                username,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                "",
                BigDecimal.ZERO,
                BigDecimal.ZERO);
    }

    public static Cart create(
            String username,
            BigDecimal discount,
            BigDecimal totalDiscount,
            String promotionCodes,
            BigDecimal subtotal,
            BigDecimal grandTotal) {
        return new Cart(
                null, username, discount, totalDiscount, promotionCodes, subtotal, grandTotal);
    }

    public CartResponse toResponse(List<CartItem> items) {
        return CartResponse.create(
                username, items, subtotal, discount, totalDiscount, grandTotal, promotionCodes);
    }
}
