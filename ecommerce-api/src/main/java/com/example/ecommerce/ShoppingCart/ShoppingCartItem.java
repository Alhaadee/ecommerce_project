package com.example.ecommerce.ShoppingCart;


import com.example.ecommerce.Products.Product;
import com.example.ecommerce.User.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor
public class ShoppingCartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"shoppingCart"})
    private AppUser user;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"shoppingCarts"})
    private Product product;
    @Column
    private Integer quantity;

    public ShoppingCartItem(AppUser user, Product product, Integer quantity){
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }
}

