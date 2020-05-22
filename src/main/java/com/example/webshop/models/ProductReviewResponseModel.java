package com.example.webshop.models;

import com.example.webshop.entities.ProductReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor @Getter @Setter
public class ProductReviewResponseModel {

    private String comment;
    private int stars;
    private String username;
    private Instant timeCreatedUTC;
    private Instant lastTimeUpdatedUTC;

    public ProductReviewResponseModel(ProductReview productReview) {
        this.comment = productReview.getComment();
        this.stars = productReview.getStars();
        this.username = productReview.getAppUser().getUsername();
        this.timeCreatedUTC = productReview.getTimeCreatedUTC();
        this.lastTimeUpdatedUTC = productReview.getLastTimeUpdatedUTC();
    }

}
