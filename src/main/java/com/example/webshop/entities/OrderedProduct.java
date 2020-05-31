package com.example.webshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor @Getter @Setter
public class OrderedProduct {

    @Id
    @GeneratedValue
    private Integer id;
    private double priceAtOrderTime;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOrder productOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
