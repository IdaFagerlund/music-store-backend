package com.example.webshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Getter @Setter
public class ProductOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Instant timeCreatedUTC;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
    @OneToMany(mappedBy = "productOrder", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderedProduct> orderedProducts;

    public ProductOrder() {
        this.timeCreatedUTC = Instant.now();
    }

    public ProductOrder(AppUser appUser, List<OrderedProduct> orderedProducts) {
        this.timeCreatedUTC = Instant.now();
        this.appUser = appUser;
        this.orderedProducts = orderedProducts;
    }

}
