package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductOrder;
import com.example.webshop.models.ProductOrderRequestModel;
import com.example.webshop.models.ProductOrderResponseModel;
import com.example.webshop.repositories.ProductOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final AppUserService appUserService;
    private final ProductService productService;

    public ProductOrderResponseModel addProductOrder(Principal principal, ProductOrderRequestModel productOrderModel) {
        AppUser appUser = appUserService.findByUsername(principal.getName());

        List<Product> products = productOrderModel.getProductIds()
                .stream().map(productService::findProductById)
                .collect(Collectors.toList());

        return new ProductOrderResponseModel(productOrderRepository.save(
                new ProductOrder(new HashSet<>(products), appUser)
        ));
    }
}
