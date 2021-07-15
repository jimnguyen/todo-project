package com.cognixia.jump.todoproject.controller;

import com.cognixia.jump.todoproject.exception.SameInputException;
import com.cognixia.jump.todoproject.model.ToDo;
import com.cognixia.jump.todoproject.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping(path = "/todo")
    public ResponseEntity<List<ToDo>> getTodos() {
        return toDoService.getAllToDos();
    }
    
    @PostMapping(path = "/todo")
    public ResponseEntity<ToDo> postTodo(@Valid @RequestBody ToDo toDo) {
    	return toDoService.addToDo(toDo);
    }

    @PutMapping(path = "/todo/update/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable int id, @Valid @RequestBody ToDo updatedTodo) throws SameInputException {
        return toDoService.updateToDo(id, updatedTodo);
    }

    @DeleteMapping(path = "/todo/delete/{id}")
    public ResponseEntity<ToDo> deleteTodo(@PathVariable int id) {
        return toDoService.deleteToDo(id);
    }
}
