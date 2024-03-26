package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.Promotion;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public List<CartItem> addItemToCart(String username, CartItem item) {
        Optional.of(item)
                .map(cartItemRepository::save)
                .orElseThrow(() -> new RuntimeException("Failed to add item to cart"));
        return cartItemRepository.findAllByUsername(username).orElseGet(ArrayList::new);
    }

    public BigDecimal sumOfSubtotalPrice(List<CartItem> items) {
        return items.stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal sumOfItemsDiscount(List<CartItem> items) {
        return items.stream().map(CartItem::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Cart createOrUpdateCart(String username, BigDecimal subtotal) {
        Cart cart = cartRepository.findByUsername(username).orElse(Cart.empty(username));
        cart.setSubtotal(subtotal);
        cart.setGrandTotal(subtotal.subtract(cart.getTotalDiscount()));
        return cartRepository.save(cart);
    }

    public Cart updateDiscountItemToCart(
            String username, BigDecimal itemDiscount, BigDecimal subtotal) {
        Cart cart = cartRepository.findByUsername(username).orElse(Cart.empty(username));
        cart.setTotalDiscount(cart.getDiscount().add(itemDiscount));
        cart.setSubtotal(subtotal);
        cart.setGrandTotal(subtotal.subtract(cart.getTotalDiscount()));
        return cartRepository.save(cart);
    }

    public Cart updateDiscountCart(
            String username,
            String promotionCode,
            BigDecimal discount,
            BigDecimal totalDiscount,
            BigDecimal subtotal) {
        Cart cart = cartRepository.findByUsername(username).orElse(Cart.empty(username));
        cart.setPromotionCodes(comma(cart.getPromotionCodes(), promotionCode));
        cart.setDiscount(discount);
        cart.setTotalDiscount(totalDiscount);
        cart.setSubtotal(subtotal);
        cart.setGrandTotal(subtotal.subtract(cart.getTotalDiscount()));
        return cartRepository.save(cart);
    }

    public List<CartItem> applyPromotionToItem(String username, Promotion promotion) {
        List<CartItem> items =
                cartItemRepository.findAllByUsername(username).orElseGet(ArrayList::new).stream()
                        .filter(item -> !item.getPromotionCodes().contains(promotion.getCode()))
                        .filter(item -> promotion.getProductSkus().contains(item.getSku()))
                        .map(item -> applyToItem(promotion, item))
                        .toList();

        this.cartItemRepository.saveAll(items);

        return cartItemRepository.findAllByUsername(username).orElseGet(ArrayList::new);
    }

    public CartItem applyToItem(Promotion promotion, CartItem item) {
        BigDecimal discount = item.getDiscount();
        item.setDiscount(discount.add(promotion.getDiscountAmount()));
        String codes = item.getPromotionCodes();
        item.setPromotionCodes(comma(codes, promotion.getCode()));
        return item;
    }

    private static String comma(String codes, String promotionCode) {
        return (codes.isEmpty()) ? promotionCode : String.join(",", codes, promotionCode);
    }

    public List<CartItem> getCartItems(String username) {
        return cartItemRepository.findAllByUsername(username).orElseGet(ArrayList::new);
    }

    public Cart applyPromotionToCart(String username, Promotion promotion) {
        List<CartItem> items =
                cartItemRepository.findAllByUsername(username).orElseGet(ArrayList::new);
        BigDecimal totalDiscount = sumOfItemsDiscount(items).add(promotion.getDiscountAmount());
        BigDecimal subtotal = sumOfSubtotalPrice(items);
        BigDecimal cartDiscount = promotion.getDiscountAmount();
        return updateDiscountCart(
                username, promotion.getCode(), cartDiscount, totalDiscount, subtotal);
    }
}
