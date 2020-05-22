package com.example.webshop.models;

import com.example.webshop.entities.AppUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDataResponseModel {

    private String username;
    private List<ProductReviewResponseModel> productReviews;
    private List<ProductOrderResponseModel> productOrders;
    private List<String> authorities;

    public UserDataResponseModel() {
    }

    public UserDataResponseModel(AppUser appUser) {
        this.username = appUser.getUsername();
        this.productReviews = appUser.getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
        this.productOrders = appUser.getProductOrders()
                .stream().map(ProductOrderResponseModel::new)
                .collect(Collectors.toList());
        this.authorities = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProductReviewResponseModel> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewResponseModel> productReviews) {
        this.productReviews = productReviews;
    }

    public List<ProductOrderResponseModel> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrderResponseModel> productOrders) {
        this.productOrders = productOrders;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}
