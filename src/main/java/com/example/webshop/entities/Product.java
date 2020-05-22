package com.example.webshop.entities;

import com.example.webshop.models.ProductRequestModel;

import javax.persistence.*;
import java.util.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private int averageReviewStars;
    private int stock;
    private boolean isFeatured;
    // Don't just delete the product right away because ongoing and completed orders containing this product may still need to
    // be around for a while longer. These marked products will however not be sent to the browse page on the frontend
    private boolean isRemoved;
    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductReview> productReviews;
    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pricing> prices;
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<ProductOrder> productOrders;

    public Product() {
    }

    public Product(ProductRequestModel productModel) {
        this.name = productModel.getName();
        this.description = productModel.getDescription();
        this.stock = productModel.getStock();
        this.isFeatured = productModel.isFeatured();
        this.isRemoved = false;
        this.productReviews = new ArrayList<>();
        Pricing pricing = new Pricing(productModel.getPrice());
        pricing.setProduct(this);
        this.prices = Arrays.asList(pricing);
        this.productOrders = new HashSet<>();
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

    public int getAverageReviewStars() {
        return averageReviewStars;
    }

    public void setAverageReviewStars(int averageReviewStars) {
        this.averageReviewStars = averageReviewStars;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public List<Pricing> getPrices() {
        return prices;
    }

    public void setPrices(List<Pricing> prices) {
        this.prices = prices;
    }

    public Set<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(Set<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

}
