package com.example.webshop.models;

import com.example.webshop.entities.Product;

public class ProductLightResponseModel {

    private int id;
    private String name;
    private String description;
    private int averageReviewStars;
    private double price;

    public ProductLightResponseModel(Product product, double price) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.averageReviewStars = product.getAverageReviewStars();
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAverageReviewStars() {
        return averageReviewStars;
    }

    public double getPrice() {
        return price;
    }

}
