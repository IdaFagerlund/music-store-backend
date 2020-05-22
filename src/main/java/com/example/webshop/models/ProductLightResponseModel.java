package com.example.webshop.models;

import com.example.webshop.entities.Product;

public class ProductLightResponseModel {

    private int id;
    private String name;
    private String description;
    private int averageReviewStars;
    private int stock;
    private boolean isFeatured;
    private double price;

    public ProductLightResponseModel() {
    }

    public ProductLightResponseModel(Product product, double price) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.averageReviewStars = product.getAverageReviewStars();
        this.stock = product.getStock();
        this.isFeatured = product.isFeatured();
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
