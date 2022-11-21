package com.example.ecommerce.Order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    public final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    


}
