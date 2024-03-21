package com.kampus.kbazaar.shopper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "shopper")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shopper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    public ShopperResponse toResponse() {
        return new ShopperResponse(this.id, this.username, this.email);
    }
}
