package com.example.webshop.services;

import com.example.webshop.entities.Product;
import com.example.webshop.models.ProductFullResponseModel;
import com.example.webshop.models.ProductLightResponseModel;
import com.example.webshop.models.ProductRequestModel;
import com.example.webshop.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UtilService utilService;

//    public static double getPriceAtOrderTime(Product product, ProductOrder productOrder) {
//        // 1. Sort the list so the prices will be browsed through from the most recent to the oldest.
//        // 2. Exclude the prices that existed after the order was placed.
//        // 3. Because the list was sorted, grabbing the first element now returns the price closest to the order time.
//        Pricing pricingWhenProductWasOrdered = product.getPrices()
//                .stream()
//                .sorted((o1, o2) -> o2.getTimeAtPricingUTC().compareTo(o1.getTimeAtPricingUTC()))
//                .filter(pricing -> productOrder.getTimeCreatedUTC().compareTo(pricing.getTimeAtPricingUTC()) > 0)
//                .findFirst()
//                .get();
//
//        return pricingWhenProductWasOrdered.getPrice();
//    }

    public List<ProductLightResponseModel> getAllProductsLightResponse() {
//        return productRepository.findAll().stream()
//                .map(product -> new ProductLightResponseModel(product, getCurrentProductPrice(product)))
//                .collect(Collectors.toList());
        return null;
    }

    public List<ProductFullResponseModel> getAllProductsFullResponse() {
//        return productRepository.findAll().stream()
//                .map(product -> new ProductFullResponseModel(product, getCurrentProductPrice(product)))
//                .collect(Collectors.toList());
        return null;
    }

    public ProductLightResponseModel addProduct(ProductRequestModel newProduct) {
//        utilService.validateThatFieldsAreNotNullOrEmpty(
//                newProduct.getName(), newProduct.getDescription()
//        );
        validateName(newProduct.getName());

        Product savedProduct = productRepository.save(new Product(newProduct));
        //return new ProductLightResponseModel(savedProduct, getCurrentProductPrice(savedProduct));
        return null;
    }

    public ProductLightResponseModel patchProduct(int id, ProductRequestModel updatedProduct) {
        Product product = findProductById(id);
        if(!updatedProduct.getName().equals(product.getName())) {
            validateName(updatedProduct.getName());
        }
        product.setPrice(updatedProduct.getPrice());
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setStock(updatedProduct.getStock());
        product.setFeatured(updatedProduct.isFeatured());

        //return new ProductLightResponseModel(productRepository.save(product), getCurrentProductPrice(product));
        return null;
    }

    public void markAsRemoved(int id) {
        Product product = findProductById(id);
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.delete(findProductById(id));
    }

//    protected void updateAverageReviewStars(int productId) {
//        Product product = findProductById(productId);
//        double newAverageReviewStars = product.getProductReviews()
//                .stream().map(ProductReview::getStars)
//                .mapToInt(x -> x)
//                .summaryStatistics()
//                .getAverage();
//        product.setAverageReviewStars((int) Math.round(newAverageReviewStars));
//        productRepository.save(product);
//    }

    protected Product findProductById(int id) {
        return null;
//        return productRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Can not access an unexisting product"));
    }

//    protected double getCurrentProductPrice(Product product) {
//        return product.getPrices()
//                .stream()
//                .max(Comparator.comparing(Pricing::getTimeAtPricingUTC))
//                .get()
//                .getPrice();
//    }

    private void validateName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        if(product.isPresent()) {
            //throw new ValidationException(409, "Unavailable name");
        }
    }

}
