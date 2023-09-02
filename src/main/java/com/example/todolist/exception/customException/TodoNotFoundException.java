package com.example.todolist.exception.customException;

public class TodoNotFoundException  extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TodoNotFoundException(String message) {
        super(message);
    }
}