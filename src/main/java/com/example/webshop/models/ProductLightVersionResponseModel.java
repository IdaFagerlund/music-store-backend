package com.example.webshop.models;

import com.example.webshop.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class ProductLightVersionResponseModel {

    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean isFeatured;
    private String category;
    private String subCategory;
    private int numberOfReviews;
    private int averageReviewStars;

    public ProductLightVersionResponseModel(Product product, int numberOfReviews, int averageReviewStars) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.isFeatured = product.isFeatured();
        this.category = product.getCategory();
        this.subCategory = product.getSubCategory();
        this.numberOfReviews = numberOfReviews;
        this.averageReviewStars = averageReviewStars;
    }

}
