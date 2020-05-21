package com.example.webshop.services;

import com.example.webshop.entities.Pricing;
import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductReview;
import com.example.webshop.exceptions.NotFoundException;
import com.example.webshop.models.ProductFullResponseModel;
import com.example.webshop.models.ProductLightResponseModel;
import com.example.webshop.models.ProductRequestModel;
import com.example.webshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductLightResponseModel> getAllProductsLightResponse() {
        return productRepository.findAll().stream()
                .filter(product -> !product.isRemoved())
                .map(ProductLightResponseModel::new)
                .collect(Collectors.toList());
    }

    public List<ProductFullResponseModel> getAllProductsFullResponse() {
        return productRepository.findAll().stream()
                .filter(product -> !product.isRemoved())
                .map(ProductFullResponseModel::new)
                .collect(Collectors.toList());
    }

    public ProductFullResponseModel addProduct(ProductRequestModel newProduct) {
        return new ProductFullResponseModel(productRepository.save(new Product(newProduct)));
    }

    public ProductFullResponseModel patchProduct(int id, ProductRequestModel updatedProduct) {
        Product product = findProductById(id);

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.addPricing(new Pricing(updatedProduct.getPrice()));

        return new ProductFullResponseModel(productRepository.save(product));
    }

    public void markAsRemoved(int id) {
        Product product = findProductById(id);
        product.setRemoved(true);
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.delete(findProductById(id));
    }

    protected void updateAverageReviewStars(int productId) {
        Product product = findProductById(productId);
        double newAverageReviewStars = product.getProductReviews()
                .stream().map(ProductReview::getStars)
                .mapToInt(x -> x)
                .summaryStatistics()
                .getAverage();
        product.setAverageReviewStars((int) Math.round(newAverageReviewStars));
        productRepository.save(product);
    }

    protected Product findProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can not update an unexisting product"));
    }

}
