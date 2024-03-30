package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.cartItem.CartItem;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "total_discount")
    private BigDecimal totalDiscount;

    @Column(name = "promotion_codes")
    private String promotionCodes;

    @Description("precisely reflect its pre-discount status")
    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Description("the final, all-inclusive amount to be paid.")
    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    // Define CartItem relationship
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String tryPush;
}
