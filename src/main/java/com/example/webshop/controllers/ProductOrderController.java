package com.example.webshop.controllers;

import com.example.webshop.models.ProductOrderRequestModel;
import com.example.webshop.models.ProductOrderResponseModel;
import com.example.webshop.services.ProductOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProductOrderResponseModel> addProductOrder(Principal principal,
                                                                     @RequestBody ProductOrderRequestModel productOrderModel) {
        return ResponseEntity.status(201).body(productOrderService.addProductOrder(principal, productOrderModel));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductOrderResponseModel>> getProductOrders(Principal principal) {
        return ResponseEntity.status(200).body(productOrderService.getProductOrders(principal));
    }

}
