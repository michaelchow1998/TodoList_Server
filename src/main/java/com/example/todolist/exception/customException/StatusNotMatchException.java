package com.example.todolist.exception.customException;

public class StatusNotMatchException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public StatusNotMatchException(String message) {
        super(message);
    }
}
