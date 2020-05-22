package com.example.webshop.models;

import com.example.webshop.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductFullResponseModel {

    private int id;
    private String name;
    private String description;
    private double price;
    private int averageReviewStars;
    private int stock;
    private boolean isFeatured;
    private List<ProductReviewResponseModel> productReviews;

    public ProductFullResponseModel() {
    }

    public ProductFullResponseModel(Product product, double price) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.averageReviewStars = product.getAverageReviewStars();
        this.stock = product.getStock();
        this.isFeatured = product.isFeatured();
        this.price = price;
        this.productReviews = product.getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
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

    public List<ProductReviewResponseModel> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewResponseModel> productReviews) {
        this.productReviews = productReviews;
    }

}
