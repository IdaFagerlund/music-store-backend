package com.example.webshop.services;

import com.example.webshop.entities.Pricing;
import com.example.webshop.entities.Product;
import com.example.webshop.exceptions.NotFoundException;
import com.example.webshop.models.ProductRequestModel;
import com.example.webshop.models.ProductResponseModel;
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

    public List<ProductResponseModel> getAllProducts() {
        return productRepository.findAll().stream()
                .filter(product -> !product.isRemoved())
                .map(ProductResponseModel::new)
                .collect(Collectors.toList());
    }

    public ProductResponseModel addProduct(ProductRequestModel newProduct) {
        return new ProductResponseModel(productRepository.save(new Product(newProduct)));
    }

    public ProductResponseModel patchProduct(int id, ProductRequestModel updatedProduct) {
        Product product = findProductById(id);

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.addPricing(new Pricing(updatedProduct.getPrice()));

        return new ProductResponseModel(productRepository.save(product));
    }

    public void markAsRemoved(int id) {
        Product product = findProductById(id);
        product.setRemoved(true);
        productRepository.save(product);
    }

    protected Product findProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can not update an unexisting product"));
    }

}
