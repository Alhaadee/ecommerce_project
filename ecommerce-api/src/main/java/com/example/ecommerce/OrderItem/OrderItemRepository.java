package com.example.ecommerce.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    // find all orderItems by orderId
    List<OrderItem> findByOrderId(Long orderId);
}
