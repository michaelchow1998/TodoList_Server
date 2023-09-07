package com.example.todolist.service;


import com.example.todolist.Enum.Status;
import com.example.todolist.dao.Todo;
import com.example.todolist.dto.RequestTodoCreateDto;
import com.example.todolist.dto.RequestTodoEditDto;
import com.example.todolist.exception.customException.TodoNotFoundException;
import com.example.todolist.repo.TodoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {

    private TodoRepo todoRepo;

    @Autowired
    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public void createTodo(RequestTodoCreateDto request){
        Date now = new Date();

        Todo todo = new Todo();
        todo.setMessage(request.getMessage());
        todo.setStatus(Status.PENDING);
        todo.setCreatedDate(now);
        todo.setModifyDate(now);
        todoRepo.save(todo);

    }

    public Page<Todo> getAllTodos(Pageable pageable){
        return todoRepo.findAll(pageable);
    }

    public Todo getTodoById(Integer id){
        return todoRepo.findTodoById(id);
    }

    public ArrayList<Todo> getTodosByStatus(Status status){
        return todoRepo.findTodosByStatus(status);
    }

    public Todo editTodo(RequestTodoEditDto requestTodoEdit){
        Todo todo = todoRepo.findTodoById(requestTodoEdit.getId());
        todo.setMessage(requestTodoEdit.getMessage());
        todo.setStatus(requestTodoEdit.getStatus());
        todo.setModifyDate(new Date());
        return todoRepo.save(todo);
    }


    public void deleteTodoById(Integer id){
        boolean idExist = todoRepo.existsById(id);
        if(!idExist){
            throw new TodoNotFoundException("Todo id not found");
        }
        Todo todo = todoRepo.findTodoById(id);
        todoRepo.delete(todo);

    }


}
