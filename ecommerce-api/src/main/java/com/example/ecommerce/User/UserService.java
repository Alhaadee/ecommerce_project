package com.example.ecommerce.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    public AppUser addUser(AppUser user){
        Optional<AppUser> userOptional = userRepository
                .findAppUserByEmail(user.getEmail());

        if (userOptional.isPresent()){
            throw  new IllegalStateException("This email is already in use.");
        }
        if(user.getEmail() != null && user.getEmail().length() > 0){
            return userRepository.save(user);
        } else{
            throw  new IllegalStateException("The email must not be empty.");
        }


    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(String.format("User with ID: %s, does not exist",id));
        }
        else{
            userRepository.deleteById(id);
        }

    }

    public AppUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new IllegalStateException(String.format("User with ID: %s, does not exist",id)));
    }
}
