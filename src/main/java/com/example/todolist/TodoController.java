package com.example.todolist;

import com.example.todolist.Enum.Status;
import com.example.todolist.dao.Todo;
import com.example.todolist.exception.customException.StatusNotMatchException;
import com.example.todolist.exception.customException.TodoNotFoundException;
import com.example.todolist.dto.RequestTodoCreateDto;
import com.example.todolist.dto.RequestTodoEditDto;
import com.example.todolist.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("api/v1/todos")
@RequiredArgsConstructor
@Slf4j
@Validated
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Create a todo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Create the todo success",
                content = { @Content(mediaType = "application/json")})})
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createTodo(@Valid @RequestBody RequestTodoCreateDto request){
        todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Todo create success");
    }

    @Operation(summary = "Get all todos")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<Todo>> getTodos(
            @RequestParam(defaultValue = "0") @Parameter(
            name ="page",
            schema = @Schema(description = "todos page",
                    type = "int"
            )
    ) int page,
            @RequestParam(defaultValue = "10") @Parameter(
            name ="size",
            schema = @Schema(description = "todos page size",
                    type = "int"
            )
    ) int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Todo> todos = todoService.getAllTodos(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @Operation(summary = "Get todo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get todos success",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Todo not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Todo> getTodoById(@Parameter(description = "id of todo") @PathVariable Integer id){
        Todo todo = todoService.getTodoById(id);
        if(todo == null){
            throw new TodoNotFoundException("todo not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    @Operation(summary = "Get todos by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get todos success",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Todo.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid status",
                    content = @Content)})
    @GetMapping("/byStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ArrayList<Todo>> getTodosByStatus(
            @RequestParam("status") @Parameter(
                    name ="status",
                    schema = @Schema(description = "todo status",
                            type = "string",
                            allowableValues = {"DONE", "PROCESSING", "PENDING", "DELETED"})
            )
            String statusString){
        try{
            Status status = Status.valueOf(statusString);
            ArrayList<Todo> todos = todoService.getTodosByStatus(status);
            return ResponseEntity.status(HttpStatus.OK).body(todos);
        }catch (IllegalArgumentException e){
            throw new StatusNotMatchException("status type not exist");
        }

    }

    @Operation(summary = "Edit todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get todos success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Todo.class))}),
            @ApiResponse(responseCode = "404", description = "Todo not found, can't edit",
                    content = @Content)})
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Todo> editTodo(@Valid @RequestBody RequestTodoEditDto request){
        try{
            Todo todo = todoService.editTodo(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(todo);
        }catch (NullPointerException e){
            throw new TodoNotFoundException("Todo id not found");
        }
    }

    @Operation(summary = "Delete todo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the todo success",
                    content = { @Content(mediaType = "application/json")})})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteTodoById(@Parameter(description = "id of todo")  @PathVariable Integer id){
        todoService.deleteTodoById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Todo delete success");

    }
}
