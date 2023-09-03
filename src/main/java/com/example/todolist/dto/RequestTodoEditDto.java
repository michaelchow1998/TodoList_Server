package com.example.todolist.dto;

import com.example.todolist.Enum.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestTodoEditDto {

    @NotNull
    private Integer id;

    @NotNull @Size(min = 2,max = 255)
    private String message;

    @NotNull
    private Status status;


}
