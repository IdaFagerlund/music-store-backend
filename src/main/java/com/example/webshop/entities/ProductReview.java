package com.example.webshop.entities;

import com.example.webshop.models.ProductReviewRequestModel;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class ProductReview {

    @Id
    @GeneratedValue
    private Integer id;
    private String comment;
    private int stars;
    private Instant timeCreatedUTC;
    private Instant lastTimeUpdatedUTC;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    public ProductReview() {
    }

    public ProductReview(String comment, int stars) {
        this.comment = comment;
        this.stars = stars;
        this.timeCreatedUTC = Instant.now();
    }

    public ProductReview(ProductReviewRequestModel productReviewModel) {
        this.comment = productReviewModel.getComment();
        this.stars = productReviewModel.getStars();
        this.timeCreatedUTC = Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
