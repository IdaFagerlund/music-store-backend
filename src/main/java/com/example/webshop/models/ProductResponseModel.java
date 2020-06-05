package com.example.webshop.models;

import com.example.webshop.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public abstract class ProductResponseModel {

    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean isFeatured;
    private String mainCategory;
    private String subCategory;

    public ProductResponseModel(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.isFeatured = product.isFeatured();
        this.mainCategory = product.getCategory();
        this.subCategory = product.getSubCategory();
    }

}
