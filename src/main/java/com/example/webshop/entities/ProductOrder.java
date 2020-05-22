package com.example.webshop.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
public class ProductOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Instant timeCreatedUTC;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
    @ManyToMany
    @JoinTable(name = "product_order_product",
            joinColumns = @JoinColumn(name = "product_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    public ProductOrder() {
    }

    public ProductOrder(Set<Product> products, AppUser appUser) {
        this.timeCreatedUTC = Instant.now();
        this.appUser = appUser;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getTimeCreatedUTC() {
        return timeCreatedUTC;
    }

    public void setTimeCreatedUTC(Instant timeCreatedUTC) {
        this.timeCreatedUTC = timeCreatedUTC;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
