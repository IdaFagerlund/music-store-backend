package com.example.webshop.controllers;

import com.example.webshop.models.ProductReviewRequestModel;
import com.example.webshop.models.ProductReviewResponseModel;
import com.example.webshop.services.ProductReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-reviews")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductReviewResponseModel> addProductReview(@PathVariable int productId,
                                                                       @RequestBody ProductReviewRequestModel productReviewModel) {
        return ResponseEntity.status(200).body(productReviewService.addProductReview(productId, productReviewModel));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductReviewResponseModel> patchProductReview(
            @PathVariable int productId, @RequestBody ProductReviewRequestModel productReviewModel) {
        return ResponseEntity.status(200).body(productReviewService.patchProductReview(productId, productReviewModel));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductReview(@PathVariable int productId) {
        productReviewService.deleteProductReview(productId);
        return ResponseEntity.status(200).build();
    }

}
