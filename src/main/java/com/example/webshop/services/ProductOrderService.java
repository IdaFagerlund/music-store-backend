package com.example.webshop.services;

import com.example.webshop.entities.OrderedProduct;
import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductOrder;
import com.example.webshop.exceptions.ValidationException;
import com.example.webshop.models.OrderedProductRequestModel;
import com.example.webshop.models.ProductOrderResponseModel;
import com.example.webshop.repositories.ProductOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final AppUserService appUserService;
    private final ProductService productService;

    public ProductOrderResponseModel addProductOrder(Principal principal, List<OrderedProductRequestModel> orderedProductModels) {
        ProductOrder productOrder = new ProductOrder();

        List<Integer> productIds = new ArrayList<>();
        List<OrderedProduct> orderedProducts = orderedProductModels.stream().map(orderedProductModel -> {
            Product product = productService.findProductById(orderedProductModel.getProductId());
            boolean isTryingToOrderInvalidQuantity = orderedProductModel.getQuantity() > product.getStock() || orderedProductModel.getQuantity() < 1;
            boolean isTryingToOrderTheSameProductTwice = productIds.contains(product.getId());
            if(isTryingToOrderInvalidQuantity || isTryingToOrderTheSameProductTwice) {
                throw new ValidationException();
            }
            productIds.add(product.getId());
            OrderedProduct orderedProduct = new OrderedProduct(product, orderedProductModel.getQuantity());
            orderedProduct.setProductOrder(productOrder);
            return orderedProduct;
        }).collect(Collectors.toList());

        productOrder.setAppUser(appUserService.findByUsername(principal.getName()));
        productOrder.setOrderedProducts(orderedProducts);
        return new ProductOrderResponseModel(productOrderRepository.save(productOrder));
    }

}
