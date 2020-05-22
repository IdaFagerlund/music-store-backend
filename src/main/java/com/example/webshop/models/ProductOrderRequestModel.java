package com.example.webshop.models;

import java.util.List;

public class ProductOrderRequestModel {

    private List<Integer> productIds;

    public ProductOrderRequestModel() {
    }

    public ProductOrderRequestModel(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

}
