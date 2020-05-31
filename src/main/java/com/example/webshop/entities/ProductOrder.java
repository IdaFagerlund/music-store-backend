package com.example.webshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@NoArgsConstructor @Getter @Setter
public class ProductOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Instant timeCreatedUTC;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
    @OneToMany(mappedBy = "productOrder", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProducts;

    public ProductOrder(List<OrderedProduct> products, AppUser appUser) {
        this.timeCreatedUTC = Instant.now();
        this.appUser = appUser;
        this.orderedProducts = products;
    }

}
