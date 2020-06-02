package com.example.webshop.models;

import com.example.webshop.entities.OrderedProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class OrderedProductResponseModel {

    private int productId;
    private String name;
    private double priceAtOrderTime;
    private int quantity;

    public OrderedProductResponseModel(OrderedProduct orderedProduct) {
        this.productId = orderedProduct.getProductId();
        this.name = orderedProduct.getProductName();
        this.priceAtOrderTime = orderedProduct.getProductPriceAtOrderTime();
        this.quantity = orderedProduct.getQuantity();
    }

}
