package com.example.ecommerce.Components;

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

    @Override
    public void run(ApplicationArguments args) throws Exception {

        AppUser user1 = new AppUser("John", LocalDate.of(2021, Month.MAY,28),"password","john@email.com");
        AppUser user2 = new AppUser("john", LocalDate.of(1999, Month.MAY, 28), "12345", "john123@email.com");

        userRepository.save(user1);
        userRepository.save(user2);

    }
}

