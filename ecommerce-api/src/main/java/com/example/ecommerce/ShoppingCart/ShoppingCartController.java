package com.example.ecommerce.ShoppingCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCartItemService shoppingCartItemService;

    public ShoppingCartController(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }


    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<ShoppingCartItem>> getShoppingCartByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(shoppingCartItemService.getShoppingCartItemsByUserId(userId), HttpStatus.OK);
    }


}
