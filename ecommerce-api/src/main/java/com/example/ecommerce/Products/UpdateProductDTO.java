package com.example.ecommerce.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdateProductDTO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;


}
