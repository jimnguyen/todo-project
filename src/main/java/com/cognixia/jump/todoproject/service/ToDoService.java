package com.cognixia.jump.todoproject.service;

import com.cognixia.jump.todoproject.model.ToDo;
import com.cognixia.jump.todoproject.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }
    
    public ResponseEntity<ToDo> addToDo(ToDo todo) {
    	
    	return ResponseEntity.status(201).body(toDoRepository.save(todo));
    	
    }
}
