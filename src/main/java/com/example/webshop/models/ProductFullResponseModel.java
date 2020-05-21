package com.example.webshop.models;

import com.example.webshop.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFullResponseModel {

    private int id;
    private String name;
    private String description;
    private double price;
    private int averageReviewStars;
    private List<ProductReviewResponseModel> productReviews = new ArrayList<>();

    public ProductFullResponseModel() {
    }

    public ProductFullResponseModel(int id, String name, String description, double price, int averageReviewStars,
                                    List<ProductReviewResponseModel> productReviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.averageReviewStars = averageReviewStars;
        this.productReviews = productReviews;
    }

    public ProductFullResponseModel(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        double currentPrice = product.getPrices().get(product.getPrices().size() - 1).getPrice();
        this.price = currentPrice;
        this.averageReviewStars = product.getAverageReviewStars();
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

    public List<ProductReviewResponseModel> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewResponseModel> productReviews) {
        this.productReviews = productReviews;
    }

}
