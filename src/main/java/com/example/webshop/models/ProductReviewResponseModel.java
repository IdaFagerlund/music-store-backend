package com.example.webshop.models;

import com.example.webshop.entities.ProductReview;

import java.time.Instant;

public class ProductReviewResponseModel {

    private String comment;
    private int stars;
    private String username;
    private Instant timeCreatedUTC;
    private Instant lastTimeUpdatedUTC;

    public ProductReviewResponseModel() {
    }

    public ProductReviewResponseModel(ProductReview productReview) {
        this.comment = productReview.getComment();
        this.stars = productReview.getStars();
        this.username = productReview.getAppUser().getUsername();
        this.timeCreatedUTC = productReview.getTimeCreatedUTC();
        this.lastTimeUpdatedUTC = productReview.getLastTimeUpdatedUTC();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getTimeCreatedUTC() {
        return timeCreatedUTC;
    }

    public void setTimeCreatedUTC(Instant timeCreatedUTC) {
        this.timeCreatedUTC = timeCreatedUTC;
    }

    public Instant getLastTimeUpdatedUTC() {
        return lastTimeUpdatedUTC;
    }

    public void setLastTimeUpdatedUTC(Instant lastTimeUpdatedUTC) {
        this.lastTimeUpdatedUTC = lastTimeUpdatedUTC;
    }

}
