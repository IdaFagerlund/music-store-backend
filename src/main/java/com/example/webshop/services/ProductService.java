package com.example.webshop.services;

import com.example.webshop.entities.Product;
import com.example.webshop.models.ProductRequestModel;
import com.example.webshop.models.ProductResponseModel;
import com.example.webshop.models.ProductReviewResponseModel;
import com.example.webshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseModel> getAllProducts() {
        return productRepository.findAll()
                .stream().map(product -> {
                    List<ProductReviewResponseModel> productReviewModels = product.getProductReviews()
                            .stream().map(productReview ->
                                    new ProductReviewResponseModel(
                                            productReview.getComment(),
                                            productReview.getStars(),
                                            productReview.getUser().getUsername()
                                    )
                            ).collect(Collectors.toList());
                    return new ProductResponseModel(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            productReviewModels
                    );
                }).collect(Collectors.toList());

    }

    public void addProduct(ProductRequestModel product) {
        productRepository.save(new Product(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new ArrayList<>()
        ));
    }

}
