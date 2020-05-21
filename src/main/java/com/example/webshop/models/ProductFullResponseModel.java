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
    private List<ProductReviewResponseModel> productReviews;

    public ProductFullResponseModel(Product product, double price) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.averageReviewStars = product.getAverageReviewStars();
        this.price = price;
        this.productReviews = product.getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
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

    public double getPrice() {
        return price;
    }

    public int getAverageReviewStars() {
        return averageReviewStars;
    }

    public List<ProductReviewResponseModel> getProductReviews() {
        return productReviews;
    }

}
