package com.example.ecommerce.Products;

import com.example.ecommerce.Order.Order;
import com.example.ecommerce.OrderItem.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity @Data
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty(message = "the name cannot be empty")
    private String name;
    @Column
    @NotBlank(message = "description is mandatory")
    private String description;
    @Column
    @NotNull(message = "price is mandatory")
    @Positive(message = "price must be greater than 0")
    private Float price;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItem> productOrderItemList;
    @Column
    @NotNull(message = "quantity is mandatory")
    @PositiveOrZero(message = "quantity must be positive or zero")
    private Integer quantity;

    public Product(String name, String description, Float price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productOrderItemList = new ArrayList<>();
        this.quantity = quantity;
    }


    public  Product(){}

}
