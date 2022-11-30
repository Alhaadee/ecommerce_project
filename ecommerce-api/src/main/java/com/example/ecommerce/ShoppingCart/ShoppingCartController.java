package com.example.ecommerce.ShoppingCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCartItemService shoppingCartItemService;

    public ShoppingCartController(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }


    @GetMapping(value = "user/{userId}")
    public ResponseEntity<List<ShoppingCartItem>> getShoppingCartByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(shoppingCartItemService.getShoppingCartItemsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{cartItemId}")
    public ResponseEntity<ShoppingCartItem> getCartItemById(@PathVariable Long cartItemId){
        return new ResponseEntity<>(shoppingCartItemService.getCartItemById(cartItemId),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> addShoppingCartItem(@RequestParam Long userId,@RequestParam Long productId,@RequestParam Integer quantity ){
        shoppingCartItemService.addShoppingCartItem(userId,productId,quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteShoppingCartItem(@PathVariable Long id){
        shoppingCartItemService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
