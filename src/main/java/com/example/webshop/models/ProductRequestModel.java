package com.example.webshop.models;

public class ProductRequestModel {

    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean isFeatured;

    public ProductRequestModel() {
    }

    public ProductRequestModel(String name, String description, double price, int stock, boolean isFeatured) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.isFeatured = isFeatured;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

}
