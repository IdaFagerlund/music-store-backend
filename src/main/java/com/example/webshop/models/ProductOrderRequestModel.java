package com.example.webshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class ProductOrderRequestModel {

    private List<Integer> productIds;

}
