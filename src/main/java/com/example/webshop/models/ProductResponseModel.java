package com.example.webshop.models;

import com.example.webshop.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor @Getter @Setter
public class ProductResponseModel {

    private int id;
    private String name;
    private String description;
    private double price;
    private int averageReviewStars;
    private int stock;
    private boolean isFeatured;
    private List<ProductReviewResponseModel> productReviews;

    public ProductResponseModel(Product product, int averageReviewStars) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.averageReviewStars = averageReviewStars;
        this.stock = product.getStock();
        this.isFeatured = product.isFeatured();
        this.price = product.getPrice();
        this.productReviews = product.getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
    }

}