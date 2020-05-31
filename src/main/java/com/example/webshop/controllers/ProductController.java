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

    @GetMapping("/all/light")
    public ResponseEntity<List<ProductLightResponseModel>> getAllProductsLightResponse() {
        return ResponseEntity.status(200).body(productService.getAllProductsLightResponse());
    }

    @GetMapping("/all/full")
    public ResponseEntity<List<ProductFullResponseModel>> getAllProductsFullResponse() {
        return ResponseEntity.status(200).body(productService.getAllProductsFullResponse());
    }

    @PostMapping("/")
    public ResponseEntity<ProductLightResponseModel> addProduct(@RequestBody ProductRequestModel product) {
        return ResponseEntity.status(201).body(productService.addProduct(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductLightResponseModel> patchProduct(@PathVariable int id,
                                                                  @RequestBody ProductRequestModel product) {
        return ResponseEntity.status(200).body(productService.patchProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(200).build();
    }

}
