package com.kampus.kbazaar.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kampus.kbazaar.promotion.Promotion;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CartServiceTest {

    @Mock private CartItemRepository cartItemRepository;

    @Mock private CartRepository cartRepository;

    @InjectMocks private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should be able to add product to cart")
    void shouldBeAbleToAddProductToCart() {
        // given
        CartItem cartItem =
                new CartItem(
                        1L,
                        "TechNinja",
                        "MOBILE-APPLE-IPHONE-12-PRO",
                        "Apple iPhone 12 Pro",
                        new BigDecimal(20990.25),
                        1,
                        BigDecimal.ZERO,
                        "");

        when(cartItemRepository.save(any())).thenReturn(cartItem);
        when(cartItemRepository.findAllByUsername("TechNinja"))
                .thenReturn(Optional.of(List.of(cartItem)));

        // when
        List<CartItem> items = cartService.addItemToCart("TechNinja", cartItem);

        assertEquals(1, items.size(), "Cart should have one item");
    }

    @Test
    @DisplayName("add multiple products to cart")
    void addMultipleProductsToCart() {
        // given
        CartItem p1 =
                new CartItem(
                        1L,
                        "TechNinja",
                        "MOBILE-APPLE-IPHONE-12-PRO",
                        "Apple iPhone 12 Pro",
                        new BigDecimal(20990.25),
                        1,
                        BigDecimal.ZERO,
                        "");
        CartItem p2 =
                new CartItem(
                        2L,
                        "TechNinja",
                        "MOBILE-SAMSUNG-GALAXY-S21",
                        "Samsung Galaxy S21 Ultra",
                        new BigDecimal(10990.25),
                        1,
                        BigDecimal.ZERO,
                        "");

        when(cartItemRepository.save(any())).thenReturn(p1, p2);
        when(cartItemRepository.findAllByUsername("TechNinja"))
                .thenReturn(Optional.of(List.of(p1, p2)));

        // when
        cartService.addItemToCart("TechNinja", p1);
        List<CartItem> items = cartService.addItemToCart("TechNinja", p2);

        // then
        assertEquals(2, items.size(), "Cart should have two items");
        assertEquals(List.of(p1, p2), items);
    }

    @Test
    @DisplayName("should be able to calculate total price")
    void shouldBeAbleToCalculateTotalPrice() {
        CartItem p1 =
                new CartItem(
                        1L,
                        "TechNinja",
                        "MOBILE-APPLE-IPHONE-12-PRO",
                        "Apple iPhone 12 Pro",
                        new BigDecimal(20990.25),
                        1,
                        BigDecimal.ZERO,
                        "");
        CartItem p2 =
                new CartItem(
                        2L,
                        "TechNinja",
                        "MOBILE-SAMSUNG-GALAXY-S21",
                        "Samsung Galaxy S21 Ultra",
                        new BigDecimal(10990.25),
                        1,
                        BigDecimal.ZERO,
                        "");
        List<CartItem> items = List.of(p1, p2);

        // when
        BigDecimal total = cartService.sumOfSubtotalPrice(items);

        // then
        assertEquals(new BigDecimal("31980.50"), total);
    }

    @Test
    @DisplayName("should return sum of discount of all items")
    void shouldReturnSumOfDiscountOfAllItems() {
        CartItem p1 =
                new CartItem(
                        1L, "TechNinja", "", "", BigDecimal.ONE, 1, new BigDecimal("10.25"), "");
        CartItem p2 = new CartItem(2L, "", "", "", BigDecimal.ONE, 1, new BigDecimal("10.75"), "");
        List<CartItem> items = List.of(p1, p2);

        // when
        BigDecimal sumItemsDiscount = cartService.sumOfItemsDiscount(items);

        // then
        assertEquals(new BigDecimal("21.00"), sumItemsDiscount);
    }

    @Test
    @DisplayName("should be able to create or update cart if cart does not exist")
    public void testCreateOrUpdateCart_NewCart() {
        String username = "TechNinja";
        BigDecimal subtotal = BigDecimal.valueOf(100.00);

        // Mock cartRepository behavior
        Cart emptyCart = Cart.empty(username);
        when(cartRepository.findByUsername(username)).thenReturn(Optional.of(emptyCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(emptyCart);

        // Call the method under test
        Cart cart = cartService.createOrUpdateCart(username, subtotal);

        // Assertions
        assertEquals(username, cart.getUsername());
        assertEquals(subtotal, cart.getSubtotal());
        assertEquals(subtotal, cart.getGrandTotal()); // No discount assumed here
    }

    @Test
    @DisplayName("should be able to create or update cart if cart already exists")
    public void testCreateOrUpdateCart_ExistingCart() {
        String username = "TechNinja";
        BigDecimal subtotal = BigDecimal.valueOf(150.00);
        BigDecimal discount = BigDecimal.valueOf(10.00);
        BigDecimal grandTotal = subtotal.subtract(discount);
        Cart existingCart = Cart.create(username, discount, discount, "", subtotal, grandTotal);
        when(cartRepository.findByUsername(username)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);

        // Call the method under test
        BigDecimal updateSubtotal = BigDecimal.valueOf(200.00);
        Cart cart = cartService.createOrUpdateCart(username, updateSubtotal);

        // Assertions
        assertEquals(username, cart.getUsername(), "Username should be same");
        assertEquals(discount, cart.getDiscount(), "Discount should be same");
        assertEquals(updateSubtotal, cart.getSubtotal(), "Subtotal should be updated");
        assertEquals(
                updateSubtotal.subtract(discount),
                cart.getGrandTotal(),
                "Grand total should be updated");
    }

    @Test
    @DisplayName(
            "should be not apply promotion to item if no matching items and applicable to entire cart")
    public void testApplyPromotionToItem_NoMatchingItems() {
        String username = "TechNinja";
        List<CartItem> items =
                Arrays.asList(
                        new CartItem(
                                1L,
                                "TechNinja",
                                "MOBILE-APPLE-IPHONE-12-PRO",
                                "Apple iPhone 12 Pro",
                                new BigDecimal(20990.25),
                                1,
                                BigDecimal.ZERO,
                                ""),
                        new CartItem(
                                2L,
                                "TechNinja",
                                "MOBILE-SAMSUNG-GALAXY-S21",
                                "Samsung Galaxy S21 Ultra",
                                new BigDecimal(10990.25),
                                1,
                                BigDecimal.ZERO,
                                ""));
        when(cartItemRepository.findAllByUsername(username)).thenReturn(Optional.of(items));
        when(cartItemRepository.saveAll(items)).thenReturn(items);

        BigDecimal discountAmount = new BigDecimal(10.00);
        Promotion promotion =
                createPromotion(
                        "FIXEDAMOUNT10",
                        "Fixed Amount $10 Off Entire Cart",
                        "Get $10 off on your entire cart purchase.",
                        "FIXED_AMOUNT",
                        discountAmount,
                        new BigDecimal(0.00),
                        "ENTIRE_CART",
                        "");

        List<CartItem> applied = cartService.applyPromotionToItem(username, promotion);

        // Assertions
        assertEquals(items, applied, "Cart items should remain same");
        for (CartItem item : applied) {
            assertFalse(
                    item.getPromotionCodes().contains(promotion.getCode()),
                    "Promotion should not be applied");
        }
    }

    @Test
    @DisplayName("should be able to apply promotion to cart if applicable to entire cart")
    public void testApplyPromotionToCart() {
        String username = "TechNinja";
        BigDecimal itemDiscount = BigDecimal.TEN;
        BigDecimal cartDiscount = new BigDecimal(990.25);
        BigDecimal subtotal = new BigDecimal(20990.25);
        BigDecimal totalDiscount = itemDiscount.add(cartDiscount);
        BigDecimal grandTotal = subtotal.subtract(totalDiscount);
        List<CartItem> items =
                Arrays.asList(
                        new CartItem(
                                1L,
                                "TechNinja",
                                "MOBILE-APPLE-IPHONE-12-PRO",
                                "Apple iPhone 12 Pro",
                                subtotal,
                                1,
                                itemDiscount,
                                ""));
        Cart existingCart =
                Cart.create(username, itemDiscount, totalDiscount, "", subtotal, grandTotal);
        when(cartItemRepository.findAllByUsername(username)).thenReturn(Optional.of(items));
        when(cartRepository.findByUsername(username)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(existingCart)).thenReturn(existingCart);

        String promotionCode = "FIXEDAMOUNT10";
        Promotion promotion =
                createPromotion(
                        promotionCode,
                        "Fixed Amount $10 Off Entire Cart",
                        "Get $10 off on your entire cart purchase.",
                        "FIXED_AMOUNT",
                        cartDiscount,
                        new BigDecimal(0.00),
                        "ENTIRE_CART",
                        "");

        Cart cart = cartService.applyPromotionToCart(username, promotion);

        assertEquals(username, cart.getUsername(), "Username should be same");
        assertEquals(promotionCode, cart.getPromotionCodes(), "Promotion code should be applied");
        assertEquals(cartDiscount, cart.getDiscount(), "Cart discount should be applied");
        assertEquals(
                totalDiscount,
                cart.getTotalDiscount(),
                "Total discount should be sum of item and cart discount");
        assertEquals(
                subtotal,
                cart.getSubtotal(),
                "Subtotal should remain same as no item price change");
        assertEquals(
                grandTotal, cart.getGrandTotal(), "Grand total should be updated with discount");
    }

    @Test
    @DisplayName("should be able to apply promotion to item if single matching item")
    public void testApplyPromotionToItem_SingleMatchingItem_WithDiscount() {
        String username = "TechNinja";
        String sku = "STATIONERY-STAPLER-SWINGLINE";
        BigDecimal existingDiscount = BigDecimal.ZERO;

        List<CartItem> existingItems =
                Collections.singletonList(
                        new CartItem(
                                1L,
                                "TechNinja",
                                "STATIONERY-STAPLER-SWINGLINE",
                                "Staplers",
                                new BigDecimal(20.25),
                                1,
                                existingDiscount,
                                ""));
        when(cartItemRepository.findAllByUsername(username)).thenReturn(Optional.of(existingItems));

        BigDecimal promotionDiscount = new BigDecimal(2.00);
        String productSkus = "STATIONERY-STAPLER-SWINGLINE,STATIONERY-PENCIL-FABER-CASTELL";
        Promotion promotion =
                createPromotion(
                        "FIXEDAMOUNT2",
                        "Fixed Amount $2 Off Specific Products",
                        "Get $2 off on specific products.",
                        "FIXED_AMOUNT",
                        promotionDiscount,
                        new BigDecimal(0.00),
                        "SPECIFIC_PRODUCTS",
                        productSkus);

        // Call the method under test
        List<CartItem> updatedItems = cartService.applyPromotionToItem(username, promotion);

        // Assertions
        assertEquals(1, updatedItems.size());
        assertEquals(username, updatedItems.get(0).getUsername());
        assertEquals(sku, updatedItems.get(0).getSku());
        assertEquals(existingDiscount.add(promotionDiscount), updatedItems.get(0).getDiscount());
        assertEquals(promotion.getCode(), updatedItems.get(0).getPromotionCodes());
    }

    @NotNull private static Promotion createPromotion(
            String code,
            String name,
            String description,
            String discountType,
            BigDecimal promotionDiscount,
            BigDecimal maxDiscountAmount,
            String applicableTo,
            String productSkus) {
        LocalDateTime startDate = LocalDateTime.of(2024, 03, 25, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 03, 25, 0, 0);
        Integer minQuantity = 0;
        Integer freeQuantity = 0;
        return new Promotion(
                1L,
                code,
                name,
                description,
                startDate,
                endDate,
                discountType,
                promotionDiscount,
                maxDiscountAmount,
                applicableTo,
                productSkus,
                minQuantity,
                freeQuantity);
    }
}
