package com.example.webshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter @Setter
public class ProductOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Instant timeCreatedUTC;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_order_product",
            joinColumns = @JoinColumn(name = "product_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    public ProductOrder(Set<Product> products, AppUser appUser) {
        this.timeCreatedUTC = Instant.now();
        this.appUser = appUser;
        this.products = products;
    }

}
