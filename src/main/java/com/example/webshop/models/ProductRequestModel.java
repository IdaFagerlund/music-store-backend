package com.example.webshop.models;

public class ProductRequestModel {

    private final String name;
    private final String description;
    private final double price;

    public ProductRequestModel(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

}
