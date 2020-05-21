package com.example.webshop.controllers;

import com.example.webshop.models.ProductFullResponseModel;
import com.example.webshop.models.ProductLightResponseModel;
import com.example.webshop.models.ProductRequestModel;
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

    //The minimum data needed on the product browse page to speed up filtering and sorting.
    @GetMapping("/all/light")
    public ResponseEntity<List<ProductLightResponseModel>> getAllProductsLightResponse() {
        return ResponseEntity.status(200).body(productService.getAllProductsLightResponse());
    }

    @GetMapping("/all/full")
    public ResponseEntity<List<ProductFullResponseModel>> getAllProductsFullResponse() {
        return ResponseEntity.status(200).body(productService.getAllProductsFullResponse());
    }

    @PostMapping("/")
    public ResponseEntity<ProductLightResponseModel> addProduct(@RequestBody ProductRequestModel productModel) {
        return ResponseEntity.status(201).body(productService.addProduct(productModel));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductLightResponseModel> patchProduct(@PathVariable int id,
                                                                  @RequestBody ProductRequestModel productModel) {
        return ResponseEntity.status(200).body(productService.patchProduct(id, productModel));
    }

    // Don't just delete the product right away because ongoing and completed orders containing this product may still need to
    // be around for a while longer. These marked products will however not be sent to the browse page on the frontend
    @PatchMapping("/mark-as-removed/{id}")
    public ResponseEntity<Void> markProductAsRemoved(@PathVariable int id) {
        productService.markAsRemoved(id);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(200).build();
    }

}
