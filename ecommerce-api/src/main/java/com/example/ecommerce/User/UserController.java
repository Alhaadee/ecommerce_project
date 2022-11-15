package com.example.ecommerce.User;

import com.example.ecommerce.CustomExceptions.InvalidEmailException;
import com.example.ecommerce.CustomExceptions.InvalidPasswordException;
import com.example.ecommerce.CustomExceptions.UserNotFoundException;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser user) throws InvalidPasswordException, InvalidEmailException {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String email,
                                              @RequestParam(required = false) String password,
                                              @RequestParam(required = false) String dob)
            throws InvalidPasswordException, UserNotFoundException, InvalidEmailException {
        return new ResponseEntity<>(userService.updateStudent(id, name, email, password, dob), HttpStatus.OK);
    }


}
