package com.example.webshop.models;

public class ProductReviewRequestModel {

    private int productId;
    private String comment;
    private int stars;

    public ProductReviewRequestModel() {
    }

    public ProductReviewRequestModel(int productId, String comment, int stars) {
        this.productId = productId;
        this.comment = comment;
        this.stars = stars;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

}
