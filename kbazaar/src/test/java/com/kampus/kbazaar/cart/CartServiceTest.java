package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.cartItem.CartItem;
import com.kampus.kbazaar.promotion.PromotionRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void testApplyPromotionCode() {

        String username = "testUser";
        PromotionRequest promotionRequest = new PromotionRequest();
        promotionRequest.setCode("TEST_CODE");
        promotionRequest.setDiscountAmount(BigDecimal.TEN);
        promotionRequest.setProductSkus(Arrays.asList("SKU1", "SKU2"));

        CartItem item1 = new CartItem();
        item1.setSku("SKU1");
        item1.setPrice(BigDecimal.valueOf(20));
        item1.setQuantity(BigDecimal.valueOf(2));

        CartItem item2 = new CartItem();
        item2.setSku("SKU3"); // SKU3 is not in the promotion
        item2.setPrice(BigDecimal.valueOf(30));
        item2.setQuantity(BigDecimal.valueOf(1));

        List<CartItem> items = Arrays.asList(item1, item2);

        Cart cart = new Cart();
        cart.setItems(items);

        when(cartRepository.findByUsername(username)).thenReturn(cart);

    }
}
