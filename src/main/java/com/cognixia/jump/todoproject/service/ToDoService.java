package com.cognixia.jump.todoproject.service;

import com.cognixia.jump.todoproject.model.ToDo;
import com.cognixia.jump.todoproject.model.User;
import com.cognixia.jump.todoproject.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    
    public ResponseEntity<ToDo> updateToDo(int id, String description, Date updatedDate){
    	
    	ToDo toDoUpdated = toDoRepository.findById(id).orElseThrow(() -> new IllegalStateException("ToDo with " + id + " does not exist"));
    	
    	toDoUpdated.setDescription(description);
    	toDoUpdated.setDueDate(updatedDate);
    	
    	return ResponseEntity.status(200).body(toDoRepository.save(toDoUpdated));
    	
    }
    
public ResponseEntity<ToDo> deleteUser(int id){
		
		Optional <ToDo> deletedToDo = toDoRepository.findById(id);
		
		if(deletedToDo.isPresent()) {
			toDoRepository.deleteById(id);
			
			return ResponseEntity.status(200).body(deletedToDo.get());
		}
		
		return ResponseEntity.status(400).body(new ToDo());
		
				
				
	}
    
    
}
