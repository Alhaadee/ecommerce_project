package com.example.ecommerce.CustomExceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException() {
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
