package com.example.webshop.controllers;

import com.example.webshop.models.ProductReviewRequestModel;
import com.example.webshop.services.ProductReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-review")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProductReview(@RequestBody ProductReviewRequestModel productReview) {
        productReviewService.addProductReview(productReview);
        return ResponseEntity.status(200).build();
    }

}
