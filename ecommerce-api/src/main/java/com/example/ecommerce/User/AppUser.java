package com.example.ecommerce.User;

import com.example.ecommerce.Order.Order;
import com.example.ecommerce.ShoppingCart.ShoppingCartItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity @Data
@Table(name = "users")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate dob;
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Order> orders;
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<ShoppingCartItem> shoppingCart;
    @Column
    private String password;
    @Column
    private String email;
    @Transient
    private Integer age;

    // todo: add json ignore to password later

    public AppUser() {
    }

    public AppUser(String name, LocalDate dob, String password, String email){
        this.dob = dob;
        this.name = name;
        this.password = password;
        this.orders = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
        this.email = email;
    }
    //custom getter
    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }



}
