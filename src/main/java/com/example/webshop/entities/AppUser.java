package com.example.webshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    //@OneToMany(mappedBy = "app_user", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ProductReview> productReviews = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<ProductReview> getProductReviews() {
//        return productReviews;
//    }
//
//    public void setProductReviews(List<ProductReview> productReviews) {
//        this.productReviews = productReviews;
//    }
}
