package com.example.webshop.controllers;

import com.example.webshop.models.ProductRequestModel;
import com.example.webshop.models.ProductResponseModel;
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

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseModel>> getProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponseModel> addProduct(@RequestBody ProductRequestModel productModel) {
        return ResponseEntity.status(201).body(productService.addProduct(productModel));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseModel> patchProduct(@PathVariable int id,
                                                             @RequestBody ProductRequestModel productModel) {
        return ResponseEntity.status(200).body(productService.patchProduct(id, productModel));
    }

    @PatchMapping("/mark-as-removed/{id}")
    public ResponseEntity<Void> markProductAsRemoved(@PathVariable int id) {
        productService.markAsRemoved(id);
        return ResponseEntity.status(200).build();
    }

}
