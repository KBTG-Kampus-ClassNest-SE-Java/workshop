package com.kampus.kbazaar.cart;

import jakarta.persistence.*;
import java.math.BigDecimal;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "promotion_codes")
    private String promotionCodes;

    @Column(name = "discount")
    private BigDecimal discount;

    @Description("total price after discount")
    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name="username", referencedColumnName = "username", insertable = false, updatable = false)
    private Cart cart;

    public CartItemResponse toCartItemResponse() {
        val dto = new CartItemResponse();

        dto.setSku(this.getSku());
        dto.setName(this.getName());
        dto.setPrice(this.getPrice());
        dto.setQuantity(this.getQuantity());
        dto.setPromotionCodes(this.getPromotionCodes());
        dto.setDiscount(this.getDiscount());

        return dto;
    }
}
