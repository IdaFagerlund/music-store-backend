package com.example.webshop.models;

import java.util.ArrayList;
import java.util.List;

public class ProductResponseModel {

    private int id;
    private String name;
    private String description;
    private double price;
    private List<ProductReviewResponseModel> productReviews = new ArrayList<>();

    public ProductResponseModel() {
    }

    public ProductResponseModel(int id, String name, String description, double price,
                                List<ProductReviewResponseModel> productReviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productReviews = productReviews;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ProductReviewResponseModel> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewResponseModel> productReviews) {
        this.productReviews = productReviews;
    }
}
