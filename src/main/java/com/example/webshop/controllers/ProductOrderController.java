package com.example.webshop.controllers;

import com.example.webshop.models.OrderedProductRequestModel;
import com.example.webshop.models.ProductOrderResponseModel;
import com.example.webshop.services.ProductOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/product-orders")
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductOrderResponseModel> addProductOrder(@RequestBody List<OrderedProductRequestModel> orderedProducts,
                                                                     Principal principal) {
        return ResponseEntity.status(201).body(productOrderService.addProductOrder(principal, orderedProducts));
    }

}
