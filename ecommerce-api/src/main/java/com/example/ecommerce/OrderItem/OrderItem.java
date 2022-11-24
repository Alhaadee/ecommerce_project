package com.example.ecommerce.OrderItem;

import com.example.ecommerce.Order.Order;
import com.example.ecommerce.Products.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
//    @JsonIgnoreProperties({"orderItemList"})
    @JsonIncludeProperties(value = {"id"})
    private Order order;


    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"productOrderItemList"})
    private Product product;

    @Column
    private Float unit_price;

    @Column
    private Integer quantity;

    public OrderItem( Order order, Product product, Float unit_price, Integer quantity) {
        this.order = order;
        this.product = product;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Float unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
