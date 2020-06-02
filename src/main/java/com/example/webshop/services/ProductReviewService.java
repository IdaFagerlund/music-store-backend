package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductReview;
import com.example.webshop.exceptions.NotFoundException;
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

    public ProductReviewResponseModel addProductReview(int productId, ProductReviewRequestModel productReviewModel, Principal principal) {
        AppUser appUser = appUserService.findByUsername(principal.getName());
        Product product = productService.findProductById(productId);

        validateProductReview(productReviewModel);
        boolean haveUserAlreadyReviewedThisProduct = appUser.getProductReviews()
                .stream().anyMatch(productReview -> productReview.getProduct().getId() == productId);
        if(haveUserAlreadyReviewedThisProduct) {
            throw new ValidationException();
        }

        ProductReview productReview = new ProductReview(productReviewModel);
        productReview.setAppUser(appUser);
        productReview.setProduct(product);

        return new ProductReviewResponseModel(productReviewRepository.save(productReview));
    }

   public ProductReviewResponseModel patchProductReview(int productId, ProductReviewRequestModel productReviewModel, Principal principal) {
       validateProductReview(productReviewModel);

       ProductReview productReview = findProductReview(
               productService.findProductById(productId),
               principal.getName()
       );

       productReview.setStars(productReviewModel.getStars());
       productReview.setComment(productReviewModel.getComment());
       productReview.setLastTimeUpdatedUTC(Instant.now());

       return new ProductReviewResponseModel(productReviewRepository.save(productReview));
   }

   public void deleteProductReview(int productId, Principal principal) {
       ProductReview productReview = findProductReview(
               productService.findProductById(productId),
               principal.getName()
       );
       productReviewRepository.delete(productReview);
   }

   public ProductReview findProductReview(Product product, String username) {
       return product.getProductReviews()
               .stream().filter(review -> review.getAppUser().getUsername().equals(username))
               .findFirst()
               .orElseThrow(NotFoundException::new);
   }

   public void validateProductReview(ProductReviewRequestModel productReviewModel) {
       boolean isReviewMissingComment = utilService.isFieldNullOrEmpty(productReviewModel.getComment());
       boolean isStarsNotAnExpectedValue = productReviewModel.getStars() < 0 || productReviewModel.getStars() > 5;

       if(isReviewMissingComment || isStarsNotAnExpectedValue) {
           throw new ValidationException();
       }
   }

}
