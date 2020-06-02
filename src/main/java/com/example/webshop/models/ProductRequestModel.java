package com.example.webshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class ProductRequestModel {

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private boolean featured;

}
