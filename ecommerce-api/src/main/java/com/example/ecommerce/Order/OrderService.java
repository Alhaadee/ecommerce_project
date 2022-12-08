package com.example.ecommerce.Order;

import com.example.ecommerce.OrderItem.OrderItem;
import com.example.ecommerce.OrderItem.OrderItemService;
import com.example.ecommerce.Products.ProductRepository;
import com.example.ecommerce.User.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemService orderItemService;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
       return orderRepository.findById(id).orElseThrow(()->new RuntimeException("Id " + id + " doesn't exist"));
    }

    public Order createOrder(OrderDTO orderDTO){
        if(orderDTO.getAddress() == null
                || orderDTO.getEmail() == null
                || !userRepository.existsById(orderDTO.getUserId())){
            throw  new RuntimeException("email or user id or address cannot be empty");
        }
        Order order = new Order();
        order.setAddress(orderDTO.getAddress());
        order.setEmail(orderDTO.getEmail());
        order.setUser(userRepository.findById(orderDTO.getUserId()).orElseThrow(()->new RuntimeException("user with id " + orderDTO.getUserId() + " doesn't exist")));
        order.setOrderDate(LocalDate.now());

        orderRepository.save(order);

        List<OrderItem> orderItemList = new ArrayList<>();
        float subtotal = 0;
        for(Long productId: orderDTO.getProductIdList()){
            orderItemList.add(orderItemService.createOrderItem(order.getId(),productId));
            //.get is not an issue as it will throw exception in previous line method.
            subtotal += productRepository.findById(productId).get().getPrice();
        }
        order.setSubTotal(subtotal);
        order.setOrderItemList(orderItemList);
        return orderRepository.save(order);



    }
}
