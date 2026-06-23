package com.ngts.my_first_app.exception;


// Represents an application-specific error.
// Custom exceptions help make error handling clearer and allow centralized handling.
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
