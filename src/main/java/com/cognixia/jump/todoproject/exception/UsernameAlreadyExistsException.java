package com.cognixia.jump.todoproject.exception;

public class UsernameAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
