package com.example.ecommerce.configurations;

import com.example.ecommerce.Products.Product;
import com.example.ecommerce.Products.ProductRepository;
import com.example.ecommerce.Products.ProductService;
import com.example.ecommerce.User.AppUser;
import com.example.ecommerce.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        AppUser user1 = new AppUser("John", LocalDate.of(2021, Month.MAY,28), passwordEncoder.encode("Password1"), "john@email.com");
        AppUser user2 = new AppUser("john", LocalDate.of(1999, Month.MAY, 28), "12345", "john123@email.com");

        userRepository.save(user1);
        userRepository.save(user2);

        Product product1 = new Product("car","modern car",123.4F,23);
        Product product2 = new Product("bike","bmx bike",123.4F,11);
        Product product3 = new Product("ps5","it is a game console",500F,2);
        Product product4 = new Product("xbox","another game console",400.4F,5);

        productRepository.saveAll(List.of(product1,product2,product3,product4));

    }
}

