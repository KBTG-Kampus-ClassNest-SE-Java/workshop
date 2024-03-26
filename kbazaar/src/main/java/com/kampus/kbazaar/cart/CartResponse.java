package com.kampus.kbazaar.cart;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private String username;

    private List<CartItem> items;

    private BigDecimal discount;

    private BigDecimal totalDiscount;

    private BigDecimal subtotal;

    private BigDecimal grandTotal;

    private String promotionCodes;

    public static CartResponse create(String username, List<CartItem> items) {
        return new CartResponse(
                username,
                items,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                "");
    }

    public static CartResponse create(
            String username,
            List<CartItem> items,
            BigDecimal subtotal,
            BigDecimal discount,
            BigDecimal totalDiscount,
            BigDecimal grandTotal,
            String promotionCodes) {
        return new CartResponse(
                username, items, discount, totalDiscount, subtotal, grandTotal, promotionCodes);
    }
}
