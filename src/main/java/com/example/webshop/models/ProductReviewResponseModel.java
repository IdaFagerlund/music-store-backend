package com.example.webshop.models;

import com.example.webshop.entities.ProductReview;

public class ProductReviewResponseModel {

    private String comment;
    private int stars;
    private String username;

    public ProductReviewResponseModel() {
    }

    public ProductReviewResponseModel(String comment, int stars, String username) {
        this.comment = comment;
        this.stars = stars;
        this.username = username;
    }

    public ProductReviewResponseModel(ProductReview productReview) {
        this.comment = productReview.getComment();
        this.stars = productReview.getStars();
        this.username = productReview.getUser().getUsername();
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

}
