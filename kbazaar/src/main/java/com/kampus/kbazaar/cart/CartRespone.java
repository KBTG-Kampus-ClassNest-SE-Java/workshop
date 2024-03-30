package com.kampus.kbazaar.cart;

import jakarta.persistence.Column;

public class CartRespone {
    private String username;
    private String sku;
    private Float price;
    private Integer quantity;
    private Float discount;


    private Float total_discount;

    private Float sub_total;

    private Float grand_total;

}
