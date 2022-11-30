package com.example.ecommerce.ShoppingCart;

import com.example.ecommerce.CustomExceptions.NotFoundException;
import com.example.ecommerce.CustomExceptions.ProductNotFoundException;
import com.example.ecommerce.CustomExceptions.UserNotFoundException;
import com.example.ecommerce.Products.Product;
import com.example.ecommerce.Products.ProductRepository;
import com.example.ecommerce.User.AppUser;
import com.example.ecommerce.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartItemService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartItemService(ShoppingCartItemRepository shoppingCartItemRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    public List<ShoppingCartItem> getShoppingCartItemsByUserId(Long userId) {
        return shoppingCartItemRepository.findByUserId(userId);
    }

    public void addShoppingCartItem(Long userId, Long productId, Integer quantity) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

        Optional<AppUser> userOptional = userRepository.findById(userId);
        shoppingCartItem.setUser(userOptional
                .orElseThrow(()-> new UserNotFoundException("user with id: "+ userId + " not found")));

        Optional<Product> productOptional = productRepository.findById(productId);
        shoppingCartItem.setProduct(productOptional
                .orElseThrow(()-> new ProductNotFoundException("product with id: "+ userId + " not found")));
        // maybe reduce quantity from product database
        if(quantity > productOptional.get().getQuantity()){
            throw new RuntimeException("Not enough stock available");
        }
        shoppingCartItem.setQuantity(quantity);
        shoppingCartItemRepository.save(shoppingCartItem);

    }

    public void deleteCartItem(Long cartItemId){
        boolean cartItemExists = shoppingCartItemRepository.existsById(cartItemId);
        if(!cartItemExists){
            throw new RuntimeException("ShoppingCartItem with Id: " + cartItemId + " doesn't exist");
        }
        shoppingCartItemRepository.deleteById(cartItemId);

    }

    public ShoppingCartItem getCartItemById(Long id) {
        return shoppingCartItemRepository.findById(id).orElseThrow(()-> new NotFoundException("CartItem with id: " + id + " not found."));
    }

    //todo add method to edit the quantity - patch request
}
