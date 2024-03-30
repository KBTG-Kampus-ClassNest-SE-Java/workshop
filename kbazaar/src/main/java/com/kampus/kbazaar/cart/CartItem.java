package com.kampus.kbazaar.cart;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name="CartItem")
public class CartItem {

    @Column(name = "username")
    private String username;
    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private Float price;
    @Column(name = "quanity")
    private Integer quantity;
    @Column(name = "promotion")
    private String promotion;
    @Column(name = "discount")
    private Float discount;
    @Column(name = "total")
    private Float total;



}
