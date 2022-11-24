package com.example.ecommerce.Order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Data
public class OrderDTO {

    private Long userId;

    private String email;

    private String address;

    private List<Long> productIdList;

    public OrderDTO(Long userId, String email, String address) {
        this.userId = userId;
        this.email = email;
        this.address = address;
        this.productIdList = new ArrayList<>();
    }

    public OrderDTO() {
    }
}
