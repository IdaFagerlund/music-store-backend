package com.example.webshop.services;

import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductReview;
import com.example.webshop.exceptions.NotFoundException;
import com.example.webshop.exceptions.ValidationException;
import com.example.webshop.models.ProductCategoryResponseModel;
import com.example.webshop.models.ProductRequestModel;
import com.example.webshop.models.ProductResponseModel;
import com.example.webshop.models.errors.ProductFieldsErrorResponseModel;
import com.example.webshop.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UtilService utilService;

    public List<ProductResponseModel> getAllProducts() {
        return productRepository.findAll().stream().map(product ->
                new ProductResponseModel(product, getAverageReviewStars(product)))
                .collect(Collectors.toList());
    }

    public ProductResponseModel addProduct(ProductRequestModel productModel) {
        validateCategories(productModel.getCategory(), productModel.getSubCategory());
        ProductFieldsErrorResponseModel errors = new ProductFieldsErrorResponseModel(
                validateName(productModel.getName()),
                validateDescription(productModel.getDescription()),
                validatePrice(productModel.getPrice()),
                validateStock(productModel.getStock())
        );
        if(utilService.doesValidationErrorsExists(errors.getNameErrorMessage(), errors.getDescriptionErrorMessage(),
                errors.getPriceErrorMessage(), errors.getStockErrorMessage())) {
            throw new ValidationException(errors);
        }

        Product savedProduct = productRepository.save(new Product(productModel));
        return new ProductResponseModel(savedProduct, getAverageReviewStars(savedProduct));
    }

    public ProductResponseModel patchProduct(int id, ProductRequestModel productModel) {
        Product product = findProductById(id);
        validateCategories(productModel.getCategory(), productModel.getSubCategory());
        ProductFieldsErrorResponseModel errors = new ProductFieldsErrorResponseModel();

        if(productModel.getDescription() != null) {
            errors.setDescriptionErrorMessage(validateDescription(productModel.getDescription()));
            product.setDescription(productModel.getDescription());
        }
        if(productModel.getPrice() != null) {
            errors.setPriceErrorMessage(validatePrice(productModel.getPrice()));
            product.setPrice(productModel.getPrice());
        }
        if(productModel.getStock() != null) {
            errors.setStockErrorMessage(validateStock(productModel.getStock()));
            product.setStock(productModel.getStock());
        }
        product.setFeatured(productModel.isFeatured());

        if(utilService.doesValidationErrorsExists(errors.getDescriptionErrorMessage(), errors.getPriceErrorMessage(),
                errors.getStockErrorMessage())) {
            throw new ValidationException(errors);
        }
        Product patchedProduct = productRepository.save(product);
        return new ProductResponseModel(patchedProduct, getAverageReviewStars(patchedProduct));
    }

    public void deleteProduct(int id) {
        productRepository.delete(findProductById(id));
    }

    public List<ProductCategoryResponseModel> getProductCategories() {
        List<ProductCategoryResponseModel> productCategories = new ArrayList<>();
        getAvailableProductCategories().forEach((key, value) ->
                productCategories.add(new ProductCategoryResponseModel(key, value))
        );
        return productCategories;
    }

    protected Product findProductById(int id) {
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    private Map<String, List<String>> getAvailableProductCategories() {
        return new HashMap<String, List<String>>(){{
            put("All", new ArrayList<>());
            put("Guitars", Arrays.asList("Electric", "Acoustic"));
            put("Pianos", Arrays.asList("Grand pianos", "Keyboards"));
            put("Drums", new ArrayList<>());
            put("Other", new ArrayList<>());
        }};
    }

    private void validateCategories(String category, String subCategory) {
        Map<String, List<String>> availableCategories = getAvailableProductCategories();
        if(availableCategories.get(category) == null || (!availableCategories.get(category).isEmpty() &&
                !availableCategories.get(category).contains(subCategory))) {
            throw new ValidationException();
        }
    }

    private int getAverageReviewStars(Product product) {
        double averageStars = product.getProductReviews()
                .stream().map(ProductReview::getStars)
                .mapToInt(x -> x)
                .summaryStatistics()
                .getAverage();
        return (int) Math.round(averageStars);
    }

    private String validateName(String name) {
        if(utilService.isFieldNullOrEmpty(name)) {
            return "Missing name";
        }
        else if(productRepository.findByName(name).isPresent()) {
            return "Unavailable name";
        }
        return null;
    }

    private String validateDescription(String description) {
        if(utilService.isFieldNullOrEmpty(description)) {
            return "Missing description";
        }
        return null;
    }

    private String validatePrice(Double price) {
        if(price == null) {
            return "Missing price";
        }
        else if(price < 0) {
            return "Price can't be a negative amount";
        }
        return null;
    }

    private String validateStock(Integer stock) {
        if(stock == null) {
            return "Missing stock";
        }
        if(stock < 0) {
            return "Stock can't be a negative amount";
        }
        return null;
    }

}
