package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductReview;
import com.example.webshop.exceptions.NotFoundException;
import com.example.webshop.models.ProductReviewRequestModel;
import com.example.webshop.repositories.AppUserRepository;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.ProductReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository appUserRepository;

    public ProductReviewService(ProductReviewRepository productReviewRepository, ProductRepository productRepository,
                                AppUserRepository appUserRepository) {
        this.productReviewRepository = productReviewRepository;
        this.productRepository = productRepository;
        this.appUserRepository = appUserRepository;
    }

    public void addProductReview(ProductReviewRequestModel productReviewModel) {
        Product product = productRepository.findById(productReviewModel.getProductId())
                .orElseThrow(() -> new NotFoundException("Invalid product"));
        AppUser appUser = appUserRepository.findById(1)
                .orElseThrow(() -> new NotFoundException("Invalid user"));

        ProductReview productReview = new ProductReview(productReviewModel.getComment(), productReviewModel.getStars());

        productReview.setProduct(product);
        productReview.setUser(appUser);

        productReviewRepository.save(productReview);
    }
}
