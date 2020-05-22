package com.example.webshop.entities;

import com.example.webshop.models.ProductRequestModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor @Getter @Setter
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private int averageReviewStars;
    private int stock;
    private boolean isFeatured;
    // Don't just delete the product right away because ongoing and completed orders containing this product may still need to
    // be around for a while longer. These marked products will however not be sent to the browse page on the frontend
    private boolean isRemoved;
    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductReview> productReviews;
    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pricing> prices;
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<ProductOrder> productOrders;

    public Product(ProductRequestModel productModel) {
        this.name = productModel.getName();
        this.description = productModel.getDescription();
        this.stock = productModel.getStock();
        this.isFeatured = productModel.isFeatured();
        this.isRemoved = false;
        this.productReviews = new ArrayList<>();
        Pricing pricing = new Pricing(productModel.getPrice());
        pricing.setProduct(this);
        this.prices = Arrays.asList(pricing);
        this.productOrders = new HashSet<>();
    }

    public void addPricing(Pricing newPricing) {
        prices.add(newPricing);
        newPricing.setProduct(this);
    }

}
