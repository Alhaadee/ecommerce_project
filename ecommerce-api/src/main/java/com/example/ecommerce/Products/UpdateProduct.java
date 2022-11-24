package com.example.ecommerce.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdateProduct {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;


}
