package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.cartItem.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartTest {
    @Test
    public void testCalculateSubTotal() {

        CartItem item1 = mock(CartItem.class);
        CartItem item2 = mock(CartItem.class);

        when(item1.getPrice()).thenReturn(new BigDecimal("10")); // Set price for item1
        when(item1.getQuantity()).thenReturn(new BigDecimal("2")); // Set quantity for item1
        when(item2.getPrice()).thenReturn(new BigDecimal("20")); // Set price for item2
        when(item2.getQuantity()).thenReturn(new BigDecimal("3")); // Set quantity for item2

        List<CartItem> items = Arrays.asList(item1, item2);

        Cart cart = new Cart();
        cart.setItems(items);
    }
}
