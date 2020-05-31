package com.example.webshop.models;

import com.example.webshop.entities.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor @Getter @Setter
public class ProductOrderResponseModel {

    private Instant timeCreatedUTC;
    private List<ProductLightResponseModel> products;

    public ProductOrderResponseModel(ProductOrder productOrder) {
        this.timeCreatedUTC = productOrder.getTimeCreatedUTC();
//        this.products = productOrder.getProducts()
//                .stream().map(product -> new ProductLightResponseModel(
//                        product,
//                        ProductService.getPriceAtOrderTime(product, productOrder))
//                )
//                .collect(Collectors.toList());
    }

}
