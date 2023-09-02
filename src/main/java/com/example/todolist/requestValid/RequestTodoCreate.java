package com.example.todolist.requestValid;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestTodoCreate {

    @NotNull @Size(min = 2,max = 255)
    private String message;

}
