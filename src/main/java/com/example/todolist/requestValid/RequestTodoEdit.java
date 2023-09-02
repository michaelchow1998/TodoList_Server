package com.example.todolist.requestValid;

import com.example.todolist.Enum.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestTodoEdit {

    private Integer id;

    private String message;

    private Status status;


}
