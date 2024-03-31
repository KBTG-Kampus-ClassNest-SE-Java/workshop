package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartResponse> getCarts() {
        List<Cart> carts = cartRepository.findAll();
        if (carts.isEmpty()) {
            throw new NotFoundException("carts not found");
        }
        List<CartResponse> cartResponses = new ArrayList<>();
        for (Cart cart : carts) {
            CartResponse cartResponse = new CartResponse();
            cartResponse.setUsername(cart.getUsername());
            cartResponse.setPromotionCodes(cart.getPromotionCodes());
            cartResponse.setDiscount(cart.getDiscount());
            cartResponse.setTotalDiscount(cart.getTotalDiscount());
            cartResponse.setSubtotal(cart.getSubtotal());
            cartResponse.setGrandTotal(cart.getGrandTotal());

            List<CartItem> cartItems = cartItemRepository.findByUsername(cart.getUsername());
            List<CartItemResponse> cartItemResponses = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                CartItemResponse cartItemResponse = new CartItemResponse();
                cartItemResponse.setSku(cartItem.getSku());
                cartItemResponse.setName(cartItem.getName());
                cartItemResponse.setPrice(cartItem.getPrice());
                cartItemResponse.setQuantity(cartItem.getQuantity());
                cartItemResponse.setPromotionCodes(cartItem.getPromotionCodes());
                cartItemResponse.setDiscount(cartItem.getDiscount());
                cartItemResponses.add(cartItemResponse);
            }
            cartResponse.setItems(cartItemResponses);
            cartResponses.add(cartResponse);
        }
        return cartResponses;
    }

    public List<CartResponse> getCartsV2() {
        List<Cart> carts = cartRepository.findAll();
        val cartResponses = new ArrayList<CartResponse>();

        for (Cart cart : carts) {
            val cartResponse = cart.toCartResponse();
            val cartItemResponses = new ArrayList<CartItemResponse>();

            for (CartItem cartItem : cart.getCartItems()) {
                CartItemResponse cartItemResponse = cartItem.toCartItemResponse();
                cartItemResponses.add(cartItemResponse);
            }

            cartResponse.setItems(cartItemResponses);
            cartResponses.add(cartResponse);
        }
        return cartResponses;
    }

    public List<CartResponse> getCartsV3() {
        List<Cart> carts = cartRepository.findAllWithItems();
        val cartResponses = new ArrayList<CartResponse>();

        for (Cart cart : carts) {
            val cartResponse = cart.toCartResponse();
            val cartItemResponses = new ArrayList<CartItemResponse>();

            for (CartItem cartItem : cart.getCartItems()) {
                CartItemResponse cartItemResponse = cartItem.toCartItemResponse();
                cartItemResponses.add(cartItemResponse);
            }

            cartResponse.setItems(cartItemResponses);
            cartResponses.add(cartResponse);
        }
        return cartResponses;
    }

    // TODO poc interface
    public BigDecimal calculateDiscountPrice(Cart cart) {
        // TODO implement
        return BigDecimal.ZERO;
    }

    public void updateGrandTotalPrice(Long id, BigDecimal discount) {
        // find by id
        // for update discount, grand total price to item
        // update discount, grand total price to cart
        // TODO implement
    }

    // example add product to cart
    public void addSkuToCart(String username, String skuCode) {
        // do insert cart item by username
        // val cart = cartRepository.save(entity);
        // val grandTotalPrice = calculateGrandTotalPrice(cart);
        // updateGrandTotalPrice();
    }
}
