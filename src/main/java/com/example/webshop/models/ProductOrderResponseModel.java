package com.example.webshop.models;

import com.example.webshop.entities.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor @Getter @Setter
public class ProductOrderResponseModel {

    private Instant timeCreatedUTC;
    private List<OrderedProductResponseModel> orderedProducts;

    public ProductOrderResponseModel(ProductOrder productOrder) {
        this.timeCreatedUTC = productOrder.getTimeCreatedUTC();
        this.orderedProducts = productOrder.getOrderedProducts()
                .stream().map(OrderedProductResponseModel::new)
                .collect(Collectors.toList());
    }

}
