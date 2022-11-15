package com.example.ecommerce.User;

import com.example.ecommerce.CustomExceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public AppUser addUser(AppUser user) throws InvalidPasswordException {
        Optional<AppUser> userOptional = userRepository
                .findAppUserByEmail(user.getEmail());

        if (userOptional.isPresent()){
            throw  new IllegalStateException("This email is already in use.");
        }
        if(user.getEmail() != null && user.getEmail().length() > 0){
            if(validatePassword(user.getPassword())){
                return userRepository.save(user);
            } else {
                throw new InvalidPasswordException("The password must be between 4-8 characters and contain one lowercase letter, one uppercase letter, one digit and no spaces.");
            }

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


    @Transactional
    public AppUser updateStudent(Long id, String name, String email, String password, String dob) {
        AppUser targetUser = getUserById(id);
        if(name != null && name.length() > 0 && !targetUser.getName().equals(name)){
            targetUser.setName(name);
        }
        if(email != null &&
                email.length() > 0 &&
                !targetUser.getEmail().equals(email)){
            Optional<AppUser> appUserOptional = userRepository.findAppUserByEmail(email);
            if(appUserOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            targetUser.setEmail(email);
        }
        if(password != null &&
                validatePassword(password) &&
                !targetUser.getPassword().equals(password)){
            targetUser.setPassword(password);
        } else{
            throw new IllegalStateException("Password is invalid.");
        }
        if(dob != null && !targetUser.getDob().toString().equals(dob)){
            LocalDate parsedDob = LocalDate.parse(dob);
            targetUser.setDob(parsedDob);
        }
        return userRepository.save(targetUser);
    }

    private boolean validatePassword(String password){
        // one lowercase , one uppercase, one digit, no spaces, 4 to 8 length
        Pattern p = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{4,8}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    // Todo: add custom exceptions, add many to many relationship after


}
