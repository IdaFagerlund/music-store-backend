package com.example.webshop.controllers;

import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductReview;
import com.example.webshop.models.ProductModel;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.ProductReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;

    public ProductController(ProductRepository productRepository, ProductReviewRepository productReviewRepository) {
        this.productRepository = productRepository;
        this.productReviewRepository = productReviewRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getTests() {
        List<Product> products = productRepository.findAll();
        ProductModel productModel = new ProductModel(products.get(0).getName(), products.get(0).getProductReviews().get(0).getComment());
        return ResponseEntity.status(200).body(productModel);
    }

    @GetMapping("/1")
    public ResponseEntity<List<ProductReview>> getTesasdts() {
        return ResponseEntity.status(200).body(productReviewRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<String> getTest2s() {

        Product p = new Product();
        p.setName("name");
        p.setPrice(10);
        ProductReview r = new ProductReview("comment1", 1);
        p.setProductReviews(Arrays.asList(r));
        r.setProduct(p);
        productRepository.save(p);
        return ResponseEntity.ok("");
    }

}
