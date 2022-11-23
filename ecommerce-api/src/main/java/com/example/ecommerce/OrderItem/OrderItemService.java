package com.example.ecommerce.OrderItem;

import com.example.ecommerce.Order.OrderRepository;
import com.example.ecommerce.Products.Product;
import com.example.ecommerce.Products.ProductRepository;
import com.example.ecommerce.Products.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderItem createOrderItem(Long orderId, Long productId){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("orderId not found")));
        Product targetProduct = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("productId not found"));
        orderItem.setProduct(targetProduct);
        orderItem.setUnit_price(targetProduct.getPrice());
        orderItem.setQuantity(1);
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
}
