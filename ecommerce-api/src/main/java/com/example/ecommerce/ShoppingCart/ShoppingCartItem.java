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
    private AppUser appUser;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"shoppingCarts"})
    private Product product;
    @Column
    private Float price;
    @Column
    private Integer quantity;

    public ShoppingCartItem(AppUser appUser, Product product, Integer quantity){
        this.appUser = appUser;
        this.product = product;
        this.price = product.getPrice();
        this.quantity = quantity;
    }
}

