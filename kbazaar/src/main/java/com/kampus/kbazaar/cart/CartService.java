package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.cartItem.CartItem;
import com.kampus.kbazaar.promotion.PromotionRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart applyPromotionCode(String username, PromotionRequest promotionRequest) {
        Cart cart = cartRepository.findByUsername(username);

        if (cart == null) {
            return null;
        }

        if (cart.getItems() != null && !cart.getItems().isEmpty()) {
            List<CartItem> items = cart.getItems();
            BigDecimal totalDiscount = BigDecimal.ZERO;

            for (CartItem item : items) {
                if (promotionRequest.getProductSkus().contains(item.getSku())) {
                    BigDecimal discountAmount = promotionRequest.getDiscountAmount();
                    item.setDiscount(discountAmount);
                    totalDiscount = totalDiscount.add(discountAmount);
                    item.setPromotion_codes(promotionRequest.getCode());
                }
            }

            cart.setTotalDiscount(totalDiscount);
            BigDecimal subTotal = calculateSubTotal(cart.getItems());
            cart.setSubtotal(subTotal);
            BigDecimal grandTotal = subTotal.subtract(totalDiscount);
            cart.setGrandTotal(grandTotal);

            cart = cartRepository.save(cart);
        }
        return cart;
    }
    private BigDecimal calculateSubTotal(List<CartItem> items) {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (CartItem item : items) {
            BigDecimal quantity = item.getQuantity();
            BigDecimal itemPrice = item.getPrice();
            BigDecimal itemSubTotal = itemPrice.multiply(quantity);
            subTotal = subTotal.add(itemSubTotal);
        }
        return subTotal;
    }
}
