package com.example.webshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ProductCategoryResponseModel {

    private String mainCategory;
    private List<String> SubCategories;

}
