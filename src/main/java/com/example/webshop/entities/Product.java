package com.example.webshop.entities;

import com.example.webshop.models.ProductRequestModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @Getter @Setter
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    @Column(length = 1200)
    private String description;
    private double price;
    private int stock;
    private boolean isFeatured;
    private String category;
    private String subCategory;
    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductReview> productReviews;

    public Product(ProductRequestModel productModel) {
        this.name = productModel.getName();
        this.description = productModel.getDescription();
        this.price = productModel.getPrice();
        this.stock = productModel.getStock();
        this.isFeatured = productModel.isFeatured();
        this.category = productModel.getCategory();
        this.subCategory = productModel.getSubCategory();
        this.productReviews = new ArrayList<>();
    }

}
