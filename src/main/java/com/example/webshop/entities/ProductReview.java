package com.example.webshop.entities;

import javax.persistence.*;

@Entity
public class ProductReview {

    @Id
    @GeneratedValue
    private Integer id;
    private String comment;
    private int stars;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    public ProductReview() {
    }

    public ProductReview(String comment, int stars) {
        this.comment = comment;
        this.stars = stars;
    }

    public ProductReview(String comment, int stars, Product product, AppUser appUser) {
        this.comment = comment;
        this.stars = stars;
        this.product = product;
        this.appUser = appUser;
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

}
