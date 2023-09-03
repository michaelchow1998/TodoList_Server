package com.example.todolist;

import com.example.todolist.dto.RequestTodoEditDto;
import com.example.todolist.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void getTodoByValidStatusTest(){
        Assertions.assertDoesNotThrow(()->{
            var res = mockMvc.perform(get("/api/v1/todos/byStatus?status=PENDING")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk()).andReturn();
        });
    }

    @Test
    void getTodoByInvalidStatusTest(){
        Assertions.assertDoesNotThrow(()->{
            var res = mockMvc.perform(get("/api/v1/todos/byStatus?status=WRONG")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().is4xxClientError()).andReturn();
        });
    }

    @Test
    void editTodoWithInvalidIDTest(){
        RequestTodoEditDto requestDto = new RequestTodoEditDto();
        requestDto.setId(0);
        Assertions.assertDoesNotThrow(()->{
            var res = mockMvc.perform(put("/api/v1/todos")
                    .content(mapper.writeValueAsBytes(requestDto))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().is4xxClientError()).andReturn();
        });
    }

    @Test
    void editTodoWithInvalidMessageLongTest(){
        RequestTodoEditDto requestDto = new RequestTodoEditDto();
        requestDto.setMessage("I");
        Assertions.assertDoesNotThrow(()->{
         var res = mockMvc.perform(put("/api/v1/todos")
                 .content(mapper.writeValueAsBytes(requestDto))
                 .contentType(MediaType.APPLICATION_JSON)
         ).andExpect(status().is4xxClientError()).andReturn();
        });
    }

    @Test
    void createTodoWithInvalidMessageLongTest(){
        RequestTodoEditDto requestDto = new RequestTodoEditDto();
        requestDto.setMessage("I");
        Assertions.assertDoesNotThrow(()->{
            var res = mockMvc.perform(post("/api/v1/todos")
                    .content(mapper.writeValueAsBytes(requestDto))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().is4xxClientError()).andReturn();
        });
    }

}