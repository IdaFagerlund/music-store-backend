package com.example.webshop.models;

import com.example.webshop.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class ProductLightVersionResponseModel extends ProductResponseModel {

    private int numberOfReviews;
    private int averageReviewStars;

    public ProductLightVersionResponseModel(Product product, int numberOfReviews, int averageReviewStars) {
        super(product);
        this.numberOfReviews = numberOfReviews;
        this.averageReviewStars = averageReviewStars;
    }

}
