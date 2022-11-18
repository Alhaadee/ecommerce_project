package com.example.ecommerce.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidProductException extends RuntimeException {
    public InvalidProductException() {
    }

    public InvalidProductException(String message) {
        super(message);
    }
}
