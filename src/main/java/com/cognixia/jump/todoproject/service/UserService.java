package com.cognixia.jump.todoproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.todoproject.model.User;
import com.cognixia.jump.todoproject.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	public User getUserById(int id) {
		
		return userRepository.getById(id);
	}
	
	
	public ResponseEntity<User> addUser(User user){
		
		return ResponseEntity.status(201).body(userRepository.save(user));
	}
	
	public ResponseEntity<User> deleteUser(int id){
		
		Optional <User> deletedUser = userRepository.findById(id);
		
		if(deletedUser.isPresent()) {
			userRepository.deleteById(id);
			
			return ResponseEntity.status(200).body(deletedUser.get());
		}
		
		return ResponseEntity.status(400).body(new User());
		
				
				
	}
	
	public ResponseEntity<User> updateUser(int id, String first, String last, String userName, String password){
		
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("user with " + id + " does not exist"));
		
		user.setFirstName(first);
		user.setLastName(last);
		user.setUserName(userName);
		user.setPassword(password);
		
		
		
		return ResponseEntity.status(200).body(userRepository.save(user));
	}
	
	
	

}
