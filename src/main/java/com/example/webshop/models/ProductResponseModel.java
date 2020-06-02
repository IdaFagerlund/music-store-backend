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
    private String category;
    private String subCategory;
    private List<ProductReviewResponseModel> productReviews;

    public ProductResponseModel(Product product, int averageReviewStars) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.averageReviewStars = averageReviewStars;
        this.stock = product.getStock();
        this.isFeatured = product.isFeatured();
        this.category = product.getCategory();
        this.subCategory = product.getSubCategory();
        this.productReviews = product.getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
    }

}