package com.cognixia.jump.todoproject.controller;

import com.cognixia.jump.todoproject.model.ToDo;
import com.cognixia.jump.todoproject.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping(path = "/todo")
    public List<ToDo> getTodos() {
        return toDoService.getAllToDos();
    }
    
    @PostMapping(path = "/todo")
    public ResponseEntity <ToDo> postTodo(@Valid @RequestBody ToDo toDo) {
    	
    	
    	return toDoService.addToDo(toDo);
    	
    }
}
