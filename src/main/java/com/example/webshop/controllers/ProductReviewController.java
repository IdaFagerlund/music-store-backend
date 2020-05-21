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
    public ResponseEntity<ProductReviewResponseModel> addProductReview(@PathVariable int productId,
                                                                       @RequestBody ProductReviewRequestModel productReviewModel,
                                                                       Principal principal) {
        return ResponseEntity.status(201).body(productReviewService.addProductReview(productId, productReviewModel, principal));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductReviewResponseModel> patchProductReview(
            @PathVariable int productId, @RequestBody ProductReviewRequestModel productReviewModel) {
        return ResponseEntity.status(200).body(productReviewService.patchProductReview(productId, productReviewModel));
    }

    //TODO: not used. or just add some endpoints for orders etc as well. or endpoints for removing past certain time or unused stuff etc.
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductReview(@PathVariable int productId) {
        productReviewService.deleteProductReview(productId);
        return ResponseEntity.status(200).build();
    }

}
