package com.example.webshop.entities;

import javax.persistence.*;

/**
 * Keep track of the pricing history for a product because what if:
 * 1. Someone buys a product with a specific price at a given time.
 * 2. The product price change a while after, for example goes on a sale.
 * 3. That someone now wants to return the product and they should get the same amount of money back they bought it for.
 */
@Entity
public class Pricing {

    @Id
    @GeneratedValue
    private Integer id;
    private String timeUTC;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Pricing() {
    }

    public Pricing(double price) {
        this.timeUTC = "implement utc time";
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeUTC() {
        return timeUTC;
    }

    public void setTimeUTC(String timeUTC) {
        this.timeUTC = timeUTC;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    //    @Id
//    @GeneratedValue
//    private Integer id;
//    private String utcTime;
//    private double price;
////    @ManyToOne(fetch = FetchType.LAZY)
////    private Product product;
//
//    public Pricinggg() {
//    }
//
//    public Pricinggg(double price) {
//        this.utcTime = "some point in time";
//        this.price = price;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUtcTime() {
//        return utcTime;
//    }
//
//    public void setUtcTime(String utcTime) {
//        this.utcTime = utcTime;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
}
