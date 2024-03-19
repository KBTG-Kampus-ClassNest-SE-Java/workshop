package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import lombok.Data;


@Data
public class Cart {
    private int userID;

    private Product[] products;

    public Cart(int userID, Product[] products) {
        this.userID = userID;
        this.products = products;
    }
}
