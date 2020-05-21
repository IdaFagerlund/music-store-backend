package com.example.webshop.models;

import com.example.webshop.entities.ProductReview;

import java.time.Instant;

public class ProductReviewResponseModel {

    private String comment;
    private int stars;
    private String username;
    private Instant timeCreatedUTC;
    private Instant lastTimeUpdatedUTC;

    public ProductReviewResponseModel(ProductReview productReview) {
        this.comment = productReview.getComment();
        this.stars = productReview.getStars();
        this.username = productReview.getUser().getUsername();
        this.timeCreatedUTC = productReview.getTimeCreatedUTC();
        this.lastTimeUpdatedUTC = productReview.getLastTimeUpdatedUTC();
    }

    public String getComment() {
        return comment;
    }

    public int getStars() {
        return stars;
    }

    public String getUsername() {
        return username;
    }

    public Instant getTimeCreatedUTC() {
        return timeCreatedUTC;
    }

    public Instant getLastTimeUpdatedUTC() {
        return lastTimeUpdatedUTC;
    }

}
