package com.kampus.kbazaar.product;

import lombok.Data;

@Data
public class Product {
    private String name;
    private String sku;

    private double price;
    private int quantity;

    public Product() {}

    public Product(String sku, String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.quantity = quantity;
    }
}
