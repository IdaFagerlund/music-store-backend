package com.example.webshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * An order contains OrderedProduct entities instead of Product entities.
 * An OrderedProduct entity is a copy taken from the Product entity when the order was placed.
 * This is done so that the Product entity can for example change price or be deleted without the order being affected.
 */
@Entity
@NoArgsConstructor @Getter @Setter
public class OrderedProduct {

    @Id
    @GeneratedValue
    private Integer id;
    private int productId;
    private String productName;
    private double productPriceAtOrderTime;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOrder productOrder;

    public OrderedProduct(Product product, int quantity) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.productPriceAtOrderTime = product.getPrice();
        this.quantity = quantity;
    }

}
