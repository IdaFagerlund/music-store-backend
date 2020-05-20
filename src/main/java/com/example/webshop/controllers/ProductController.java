package com.example.webshop.controllers;

import com.example.webshop.models.ProductRequestModel;
import com.example.webshop.models.ProductResponseModel;
import com.example.webshop.models.ProductReviewRequestModel;
import com.example.webshop.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductResponseModel>> getProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/add-product")
    public ResponseEntity<Void> addProduct(@RequestBody ProductRequestModel product) {
        productService.addProduct(product);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/add-review")
    public ResponseEntity<Void> addProductReview(@RequestBody ProductReviewRequestModel productReview) {
        productService.addProductReview(productReview);
        return ResponseEntity.status(200).build();
    }

}
