package com.example.todolist.repo;

import com.example.todolist.Enum.Status;
import com.example.todolist.dao.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

    Todo findTodoById(Integer id);

    ArrayList<Todo> findTodosByStatus(Status status);
}
