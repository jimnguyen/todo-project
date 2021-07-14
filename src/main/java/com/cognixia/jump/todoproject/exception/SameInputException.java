package com.cognixia.jump.todoproject.exception;

public class SameInputException extends Exception {

    private static final long serialVersionUID = 1L;

    public SameInputException(String type) {
        super("The " + type + " is the same as before, please change it");
    }
}
