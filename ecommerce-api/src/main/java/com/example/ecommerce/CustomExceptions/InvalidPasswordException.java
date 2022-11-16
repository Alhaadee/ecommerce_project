package com.example.ecommerce.CustomExceptions;

public class InvalidPasswordException extends RuntimeException{

    public  InvalidPasswordException(String message){
        super(message);
    }

    public InvalidPasswordException() {
    }
}
