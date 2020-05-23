package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductReview;
import com.example.webshop.exceptions.ValidationException;
import com.example.webshop.models.ProductReviewRequestModel;
import com.example.webshop.models.ProductReviewResponseModel;
import com.example.webshop.repositories.ProductReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final ProductService productService;
    private final AppUserService appUserService;
    private final UtilService utilService;

    public List<ProductReviewResponseModel> getReviewsForProduct(int productId) {
        return productService.findProductById(productId).getProductReviews()
                .stream().map(ProductReviewResponseModel::new)
                .collect(Collectors.toList());
    }

    public ProductReviewResponseModel addProductReview(int productId, ProductReviewRequestModel productReviewModel,
                                                       Principal principal) {
        utilService.validateThatFieldsAreNotNullOrEmpty(productReviewModel.getComment());
        Product product = productService.findProductById(productId);
        AppUser appUser = appUserService.findByUsername(principal.getName());
        validateProductHasNotBeenReviewedByUsedBefore(product, appUser);

        ProductReview productReview = new ProductReview(productReviewModel);
        productReview.setProduct(product);
        productReview.setAppUser(appUser);

        ProductReview savedReview = productReviewRepository.save(productReview);
        productService.updateAverageReviewStars(productId);
        return new ProductReviewResponseModel(savedReview);
    }

    public ProductReviewResponseModel patchProductReview(int productId,
                                                         ProductReviewRequestModel updatedProductReview,
                                                         Principal principal) {
        Product product = productService.findProductById(productId);
        ProductReview productReview = product.getProductReviews()
                .stream().filter(review -> review.getAppUser().getUsername().equals(principal.getName()))
                .findFirst().get();

        productReview.setComment(updatedProductReview.getComment());
        productReview.setStars(updatedProductReview.getStars());
        productReview.setLastTimeUpdatedUTC(Instant.now());

        ProductReview savedReview = productReviewRepository.save(productReview);
        productService.updateAverageReviewStars(productId);
        return new ProductReviewResponseModel(savedReview);
    }

    public void deleteProductReview(int productId) {
        Product product = productService.findProductById(productId);
        ProductReview productReview = product.getProductReviews()
                .stream().filter(review -> review.getAppUser().getId() == 1)
                .findFirst().get();

        productReview.getAppUser().getProductReviews().remove(productReview);
        productReview.getProduct().getProductReviews().remove(productReview);

        productReviewRepository.delete(productReview);
        productService.updateAverageReviewStars(productId);
    }

    private void validateProductHasNotBeenReviewedByUsedBefore(Product product, AppUser appUser) {
        appUser.getProductReviews()
                .stream().filter(product.getProductReviews()::contains)
                .findAny()
                .orElseThrow(() -> new ValidationException(409, "Can not review the same product twice"));
    }

}
