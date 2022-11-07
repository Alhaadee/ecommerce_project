package com.example.ecommerce.User;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class UserService {


    public List<AppUser> getUsers() {
        return List.of(new AppUser("john", LocalDate.of(1999, Month.MAY,28),"12345","john@email.com"));

    }
}
