package com.example.webshop.entities;

import javax.persistence.*;
import java.time.Instant;

/**
 * Keep track of the pricing history for a product because what if:
 * 1. Someone buys a product with a specific price at a given time.
 * 2. The product price change a while after, for example goes on a sale.
 * 3. That someone decides to return the product and they should get the same amount of money back they bought it for.
 */
@Entity
public class Pricing {

    @Id
    @GeneratedValue
    private Integer id;
    private Instant timeAtPricingUTC;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Pricing() {
    }

    public Pricing(double price) {
        this.timeAtPricingUTC = Instant.now();
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getTimeAtPricingUTC() {
        return timeAtPricingUTC;
    }

    public void setTimeAtPricingUTC(Instant timeAtPricingUTC) {
        this.timeAtPricingUTC = timeAtPricingUTC;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
