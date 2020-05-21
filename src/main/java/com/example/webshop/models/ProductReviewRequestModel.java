package com.example.webshop.models;

public class ProductReviewRequestModel {

    private final String comment;
    private final int stars;

    public ProductReviewRequestModel(String comment, int stars) {
        this.comment = comment;
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public int getStars() {
        return stars;
    }

}
