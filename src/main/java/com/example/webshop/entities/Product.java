package com.example.webshop.entities;

import com.example.webshop.models.ProductRequestModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    // Don't just delete the product right away because ongoing and completed orders containing this product may still need to
    // be around for a while longer. At the same time, be able to ignore these products when browsing the current inventory
    private boolean isRemoved;
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProductReview> productReviews;
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pricing> prices;

    public Product() {
    }

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.isRemoved = false;
        this.productReviews = new ArrayList<>();
        Pricing pricing = new Pricing(price);
        pricing.setProduct(this);
        this.prices = Arrays.asList(pricing);
    }

    public Product(ProductRequestModel productModel) {
        this.name = productModel.getName();
        this.description = productModel.getDescription();
        this.isRemoved = false;
        this.productReviews = new ArrayList<>();
        Pricing pricing = new Pricing(productModel.getPrice());
        pricing.setProduct(this);
        this.prices = Arrays.asList(pricing);
    }

    public void addPricing(Pricing newPricing) {
        prices.add(newPricing);
        newPricing.setProduct(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Pricing> getPrices() {
        return prices;
    }

    public void setPrices(List<Pricing> prices) {
        this.prices = prices;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

}
