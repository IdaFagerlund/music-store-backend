package com.example.webshop.models;

import com.example.webshop.entities.ProductOrder;
import com.example.webshop.services.ProductService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ProductOrderResponseModel {

    private Instant timeCreatedUTC;
    private List<ProductLightResponseModel> products;

    public ProductOrderResponseModel(ProductOrder productOrder) {
        this.timeCreatedUTC = productOrder.getTimeCreatedUTC();
        this.products = productOrder.getProducts()
                .stream().map(product -> new ProductLightResponseModel(
                        product,
                        ProductService.getPriceAtOrderTime(product, productOrder))
                )
                .collect(Collectors.toList());
    }

    public Instant getTimeCreatedUTC() {
        return timeCreatedUTC;
    }

    public List<ProductLightResponseModel> getProducts() {
        return products;
    }

}
