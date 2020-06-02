package com.example.webshop.controllers;

import com.example.webshop.models.ProductCategoryResponseModel;
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

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseModel>> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponseModel> addProduct(@RequestBody ProductRequestModel product) {
        return ResponseEntity.status(201).body(productService.addProduct(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseModel> patchProduct(@PathVariable int id,
                                                                  @RequestBody ProductRequestModel product) {
        return ResponseEntity.status(200).body(productService.patchProduct(id, product));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategoryResponseModel>> getProductCategories() {
        return ResponseEntity.status(200).body(productService.getProductCategories());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(200).build();
    }

}
