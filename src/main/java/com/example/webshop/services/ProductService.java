package com.example.webshop.services;

import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductReview;
import com.example.webshop.exceptions.NotFoundException;
import com.example.webshop.models.ProductRequestModel;
import com.example.webshop.models.ProductResponseModel;
import com.example.webshop.models.ProductReviewRequestModel;
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
                            .stream().map(productReviewModel ->
                                    new ProductReviewResponseModel(
                                            productReviewModel.getComment(),
                                            productReviewModel.getStars(),
                                            "fake username"
                                    )
                            ).collect(Collectors.toList());
                    ProductResponseModel productModel = new ProductResponseModel(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            productReviewModels
                    );
                    return productModel;
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

    public void addProductReview(ProductReviewRequestModel productReviewModel) {
        Product product = productRepository.findById(productReviewModel.getProductId())
                .orElseThrow(() -> new NotFoundException("Invalid product id"));

        ProductReview productReview = new ProductReview(productReviewModel.getComment(), productReviewModel.getStars());

        productReview.setProduct(product);
        product.getProductReviews().add(productReview);

        productRepository.save(product);
    }

}
