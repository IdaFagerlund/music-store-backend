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

    public static double getPriceAtOrderTime(Product product) {
        return product.getPrices().get(product.getPrices().size() - 1).getPrice(); //TODO
    }

    public List<ProductLightResponseModel> getAllProductsLightResponse() {
        return productRepository.findAll().stream()
                .filter(product -> !product.isRemoved())
                .map(product -> new ProductLightResponseModel(product, getCurrentProductPrice(product)))
                .collect(Collectors.toList());
    }

    public List<ProductFullResponseModel> getAllProductsFullResponse() {
        return productRepository.findAll().stream()
                .filter(product -> !product.isRemoved())
                .map(product -> new ProductFullResponseModel(product, getCurrentProductPrice(product)))
                .collect(Collectors.toList());
    }

    public ProductLightResponseModel addProduct(ProductRequestModel newProduct) {
        Product savedProduct = productRepository.save(new Product(newProduct));
        return new ProductLightResponseModel(savedProduct, getCurrentProductPrice(savedProduct));
    }

    public ProductLightResponseModel patchProduct(int id, ProductRequestModel updatedProduct) {
        Product product = findProductById(id);

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.addPricing(new Pricing(updatedProduct.getPrice()));

        return new ProductLightResponseModel(productRepository.save(product), getCurrentProductPrice(product));
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

    protected double getCurrentProductPrice(Product product) {
        return product.getPrices().get(product.getPrices().size() - 1).getPrice();
    }

}
