package com.example.ecommerce.Order;

import com.example.ecommerce.OrderItem.OrderItem;
import com.example.ecommerce.User.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"orders"})
    private AppUser user;

    @OneToMany(mappedBy = "order")
    @JsonIgnoreProperties({"order"})
    private List<OrderItem> orderItemList;
    @Column
    private LocalDate orderDate;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private Float subTotal;

    public Order( AppUser user, LocalDate orderDate, String email, String address, float subTotal) {
        this.user = user;
        this.orderItemList = new ArrayList<>();
        this.orderDate = orderDate;
        this.email = email;
        this.address = address;
        this.subTotal = subTotal;
    }



    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }
}
