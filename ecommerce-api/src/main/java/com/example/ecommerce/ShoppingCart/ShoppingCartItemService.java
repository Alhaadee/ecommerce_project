package com.example.ecommerce.ShoppingCart;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartItemService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;

    public ShoppingCartItemService(ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }


    public List<ShoppingCartItem> getShoppingCartItemsByUserId(Long userId) {
        return shoppingCartItemRepository.findByUserId(userId);
    }
}
