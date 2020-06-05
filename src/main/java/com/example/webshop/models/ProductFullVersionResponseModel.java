package com.example.webshop.models;

import com.example.webshop.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor @Getter @Setter
public class ProductFullVersionResponseModel extends ProductResponseModel {

    private List<ProductReviewResponseModel> reviews;

    public ProductFullVersionResponseModel(Product product) {
        super(product);
        this.reviews = product.getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
    }

}