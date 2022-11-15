package com.example.ecommerce.CustomExceptions;

public class InvalidEmailException extends Exception{
    public InvalidEmailException() {
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
