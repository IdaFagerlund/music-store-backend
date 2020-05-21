package com.example.webshop.models;

import com.example.webshop.entities.AppUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDataResponseModel {

    private String username;
    private List<String> authorities;
    private List<ProductReviewResponseModel> productReviews;
    private List<ProductOrderResponseModel> productOrders;

    public UserDataResponseModel(AppUser appUser) {
        this.username = appUser.getUsername();
        this.authorities = new ArrayList<>();
        this.productReviews = appUser.getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
        this.productOrders = appUser.getProductOrders()
                .stream().map(ProductOrderResponseModel::new)
                .collect(Collectors.toList());
    }

    public String getUsername() {
        return username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public List<ProductReviewResponseModel> getProductReviews() {
        return productReviews;
    }

    public List<ProductOrderResponseModel> getProductOrders() {
        return productOrders;
    }

}
