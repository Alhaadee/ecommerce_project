package com.example.ecommerce.Components;

import com.example.ecommerce.Products.Product;
import com.example.ecommerce.Products.ProductRepository;
import com.example.ecommerce.Products.ProductService;
import com.example.ecommerce.User.AppUser;
import com.example.ecommerce.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        AppUser user1 = new AppUser("John", LocalDate.of(2021, Month.MAY,28),"password","john@email.com");
        AppUser user2 = new AppUser("john", LocalDate.of(1999, Month.MAY, 28), "12345", "john123@email.com");

        userRepository.save(user1);
        userRepository.save(user2);

        Product product1 = new Product("car","modern car",123.4F,23);
        productRepository.save(product1);

    }
}

