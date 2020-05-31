package com.example.webshop.models;

import com.example.webshop.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class ProductLightResponseModel {

    private int id;
    private String name;
    private String description;
    private int averageReviewStars;
    private int stock;
    private boolean isFeatured;
    private double price;

    public ProductLightResponseModel(Product product, double price) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        //this.averageReviewStars = product.getAverageReviewStars();
        this.stock = product.getStock();
        this.isFeatured = product.isFeatured();
        this.price = price;
    }

}
