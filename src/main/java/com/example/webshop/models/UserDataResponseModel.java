package com.example.webshop.models;

import com.example.webshop.entities.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor @Getter @Setter
public class UserDataResponseModel {

    private String username;
    private List<ProductReviewResponseModel> productReviews;
    private List<ProductOrderResponseModel> productOrders;
    private List<String> roles;

    public UserDataResponseModel(AppUser appUser) {
        this.username = appUser.getUsername();
        this.productReviews = appUser.getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
        this.productOrders = appUser.getProductOrders()
                .stream().map(ProductOrderResponseModel::new)
                .collect(Collectors.toList());
        this.roles = appUser.getUserRoles()
                .stream().map(userRole -> userRole.getUserRole().toString())
                .collect(Collectors.toList());
    }

}
