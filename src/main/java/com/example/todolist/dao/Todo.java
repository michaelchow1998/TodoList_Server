package com.example.todolist.dao;

import com.example.todolist.Enum.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="todo")
@Getter
@Setter
@ToString
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="message", length = 255)
    private String message;

    @Column(name = "status", nullable = false)
    private Status status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "modify_date")
    private Date modifyDate;

}
