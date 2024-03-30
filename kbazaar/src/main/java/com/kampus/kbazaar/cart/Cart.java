package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(name = "username")
    private String username;

    @Column(name = "promotion")
    private String promotion;
    @Column(name = "discount")
    private Float discount;
    @Column(name = "total_discount")
    private Float total_discount;
    @Column(name = "sub_total")
    private Float sub_total;
    @Column(name = "grand_total")
    private Float grand_total;

    @OneToMany(mappedBy = "username")
    private List<CartItem> cartItems;


    public Cart(long ID, String username, String promotion, Float discount, Float total_discount, Float sub_total, Float grand_total) {
        this.ID = ID;
        this.username = username;
        this.promotion = promotion;
        this.discount = discount;
        this.total_discount = total_discount;
        this.sub_total = sub_total;
        this.grand_total = grand_total;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(Float total_discount) {
        this.total_discount = total_discount;
    }

    public Float getSub_total() {
        return sub_total;
    }

    public void setSub_total(Float sub_total) {
        this.sub_total = sub_total;
    }

    public Float getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(Float grand_total) {
        this.grand_total = grand_total;
    }
}
