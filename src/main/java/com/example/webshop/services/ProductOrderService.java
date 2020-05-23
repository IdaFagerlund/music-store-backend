package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.entities.Product;
import com.example.webshop.entities.ProductOrder;
import com.example.webshop.exceptions.ValidationException;
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
        validateOrder(productOrderModel);
        AppUser appUser = appUserService.findByUsername(principal.getName());

        List<Product> products = productOrderModel.getOrderedProducts()
                .stream().map(orderedProduct -> productService.findProductById(orderedProduct.getId()))
                .collect(Collectors.toList());

        return new ProductOrderResponseModel(productOrderRepository.save(
                new ProductOrder(new HashSet<>(products), appUser)
        ));
    }

    public List<ProductOrderResponseModel> getProductOrders(Principal principal) {
        AppUser appUser = appUserService.findByUsername(principal.getName());
        return appUser.getProductOrders()
                .stream().map(ProductOrderResponseModel::new)
                .collect(Collectors.toList());
    }

    public void validateOrder(ProductOrderRequestModel productOrderModel) {
        if (productOrderModel.getOrderedProducts().isEmpty()) {
            throw new ValidationException(400, "Can not place an empty order");
        }
//        List<OrderedProductRequestModel> productsWithNotEnoughStock = productOrderModel.getOrderedProducts()
//                .stream().filter(orderedProduct ->
//                    productService.findProductById(orderedProduct.getId())
//                            .getStock() - orderedProduct.getQuantity() < 0
//                )
//                .collect(Collectors.toList());
//        if(!productsWithNotEnoughStock.isEmpty()) {
//            throw new ValidationException(400, "Can not order more of a product than exists in stock");
//        }
    }

}
