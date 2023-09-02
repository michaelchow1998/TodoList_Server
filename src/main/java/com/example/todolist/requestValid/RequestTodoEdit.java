package com.example.todolist.requestValid;

import com.example.todolist.Enum.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestTodoEdit {

    @NotNull
    private Integer id;

    @NotNull @Size(min = 2,max = 255)
    private String message;

    @NotNull
    private Status status;


}
