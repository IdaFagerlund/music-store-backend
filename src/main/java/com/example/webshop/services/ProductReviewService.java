package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductReview;
import com.example.webshop.exceptions.NotFoundException;
import com.example.webshop.models.ProductReviewRequestModel;
import com.example.webshop.models.ProductReviewResponseModel;
import com.example.webshop.repositories.AppUserRepository;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final ProductService productService;
    private final AppUserRepository appUserRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductReviewService(ProductReviewRepository productReviewRepository, ProductService productService,
                                AppUserRepository appUserRepository) {
        this.productReviewRepository = productReviewRepository;
        this.productService = productService;
        this.appUserRepository = appUserRepository;
    }

    public ProductReviewResponseModel addProductReview(int productId, ProductReviewRequestModel productReviewModel) {
        Product product = productService.findProductById(productId);
        AppUser appUser = GETUSER_REMOVEWHENSECURITYISDONE();

        ProductReview productReview = new ProductReview(productReviewModel);
        productReview.setProduct(product);
        productReview.setUser(appUser);

        return new ProductReviewResponseModel(productReviewRepository.save(productReview));
    }

    public ProductReviewResponseModel patchProductReview(int productId,
                                                         ProductReviewRequestModel updatedProductReview) {
        Product product = productService.findProductById(productId);
        ProductReview productReview = product.getProductReviews()
                .stream().filter(review -> review.getUser().getId() == 1)
                .findFirst().get();

        productReview.setComment(updatedProductReview.getComment());
        productReview.setStars(updatedProductReview.getStars());
        productReview.setLastTimeUpdatedUTC(Instant.now());

        return new ProductReviewResponseModel(productReviewRepository.save(productReview));
    }

    public void deleteProductReview(int productId) {
        Product product = productService.findProductById(productId);
        ProductReview productReview = product.getProductReviews()
                .stream().filter(review -> review.getUser().getId() == 1)
                .findFirst().get();

        productReview.getUser().getProductReviews().remove(productReview);
        productReview.getProduct().getProductReviews().remove(productReview);

        productReviewRepository.delete(productReview);
    }

    private AppUser GETUSER_REMOVEWHENSECURITYISDONE() { //TODO: hhheeere. use the principal from security
        return appUserRepository.findById(1)
                .orElseThrow(() -> new NotFoundException("Invalid user"));
    }

}
