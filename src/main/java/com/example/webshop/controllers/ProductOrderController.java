package com.example.webshop.controllers;

import com.example.webshop.models.ProductOrderRequestModel;
import com.example.webshop.models.ProductOrderResponseModel;
import com.example.webshop.services.ProductOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/product-order")
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductOrderResponseModel> addProductOrder(Principal principal,
                                                                     @RequestBody ProductOrderRequestModel productOrderModel) {
        return ResponseEntity.status(201).body(productOrderService.addProductOrder(principal, productOrderModel));
    }

}
