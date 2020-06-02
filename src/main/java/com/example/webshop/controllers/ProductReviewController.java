package com.example.webshop.controllers;

import com.example.webshop.models.ProductReviewRequestModel;
import com.example.webshop.models.ProductReviewResponseModel;
import com.example.webshop.services.ProductReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/product-reviews")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductReviewResponseModel>> getReviewsForProduct(@PathVariable int productId) {
        return ResponseEntity.status(200).body(productReviewService.getReviewsForProduct(productId));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductReviewResponseModel> addProductReview(Principal principal, @PathVariable int productId,
                                                                       @RequestBody ProductReviewRequestModel productReview) {
        return ResponseEntity.status(201).body(productReviewService.addProductReview(productId, productReview, principal));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductReviewResponseModel> patchProductReview(Principal principal, @PathVariable int productId,
                                                                         @RequestBody ProductReviewRequestModel productReview) {
        return ResponseEntity.status(200).body(productReviewService.patchProductReview(productId, productReview, principal));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductReview(@PathVariable int productId, Principal principal) {
        productReviewService.deleteProductReview(productId, principal);
        return ResponseEntity.status(200).build();
    }

}
