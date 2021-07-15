package com.cognixia.jump.todoproject.service;

import com.cognixia.jump.todoproject.exception.SameInputException;
import com.cognixia.jump.todoproject.model.ToDo;
import com.cognixia.jump.todoproject.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<ToDo> updateToDo(int id, ToDo updatedToDo) throws SameInputException {
        ToDo currentToDo = toDoRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("ToDo with " + id + " does not exist")
        );

        // check description
        if (updatedToDo.getDescription() != null) {
            if (updatedToDo.getDescription().trim().length() < 1) {
                throw new IllegalArgumentException("Please enter a valid description");
            } else if (updatedToDo.getDescription().equals(currentToDo.getDescription())) {
                throw new SameInputException("description");
            } else {
                currentToDo.setDescription(updatedToDo.getDescription());
            }
        } else {
            currentToDo.setDescription(currentToDo.getDescription());
        }

        // check due date
        if (updatedToDo.getDueDate() != null) {
            if (updatedToDo.getDueDate().toString().trim().length() < 1) {
                throw new IllegalArgumentException("Please enter a valid due date");
            } else if (updatedToDo.getDueDate().equals(currentToDo.getDueDate())) {
                throw new SameInputException("due date");
            } else {
                currentToDo.setDueDate(updatedToDo.getDueDate());
            }
        } else {
            currentToDo.setDueDate(currentToDo.getDueDate());
        }
        return ResponseEntity.status(200).body(toDoRepository.save(currentToDo));
    }

    public ResponseEntity<ToDo> deleteToDo(int id) {
        Optional<ToDo> deletedToDo = toDoRepository.findById(id);
        if (deletedToDo.isPresent()) {
            toDoRepository.deleteById(id);
            return ResponseEntity.status(200).body(deletedToDo.get());
        }
        return ResponseEntity.status(400).body(new ToDo());
    }
}
