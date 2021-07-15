package com.cognixia.jump.todoproject.service;

import java.util.List;
import java.util.Optional;

import com.cognixia.jump.todoproject.exception.ResourceNotFoundException;
import com.cognixia.jump.todoproject.exception.SameInputException;
import com.cognixia.jump.todoproject.exception.UsernameAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.todoproject.model.User;
import com.cognixia.jump.todoproject.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(int id) {
		return userRepository.getById(id);
	}

	public ResponseEntity<User> addUser(User user) {
		return ResponseEntity.status(201).body(userRepository.save(user));
	}

	public ResponseEntity<User> deleteUser(int id) {
		Optional<User> deletedUser = userRepository.findById(id);

		if (deletedUser.isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.status(200).body(deletedUser.get());
		}

		return ResponseEntity.status(400).body(new User());
	}

	public ResponseEntity<User> updateUser(int id, User updatedUser) throws SameInputException, ResourceNotFoundException, UsernameAlreadyExistsException {
		User currentUser = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("user with " + id + " does not exist")
		);
		
		
		//check first name
		if(updatedUser.getFirstName().trim().length() <1  )	{
			
			 throw new IllegalArgumentException("Please enter a valid first name");
			
		}else if(updatedUser.getFirstName().equals(currentUser.getFirstName())){
			
			throw new SameInputException("first name");
		}
		else {
			currentUser.setFirstName(updatedUser.getFirstName());
		}
		
		
		//check last name
		if(updatedUser.getLastName().trim().length() <1  )	{
			
			 throw new IllegalArgumentException("Please enter a valid last name");
			
		}else if(updatedUser.getLastName().equals(currentUser.getLastName())){
			
			throw new SameInputException("last name");
		}
		else {
			currentUser.setLastName(updatedUser.getLastName());
		}
		
		
		//check username
		if(updatedUser.getUsername().trim().length() <1  )	{
			
			 throw new IllegalArgumentException("Please enter a valid username");
			
		}else if(updatedUser.getUsername().equals(currentUser.getUsername())){
			
			throw new SameInputException("username");
		}
		else {
			
			Optional <User> existingUser = userRepository.findByUsername(updatedUser.getUsername());
			if(!existingUser.isPresent()) {
			currentUser.setUsername(updatedUser.getUsername());
			}else {
				throw new UsernameAlreadyExistsException("A username with the name " + updatedUser.getUsername() + " already exist. Please pick another username");
			}
			
		}
		
		
		//check password
		
		if(updatedUser.getPassword().trim().length() <1  )	{
			
			 throw new IllegalArgumentException("Please enter a valid password to update");
			
		}else if(updatedUser.getPassword().equals(currentUser.getPassword())){
			
			throw new SameInputException("password");
		}
		else {
			currentUser.setPassword(updatedUser.getPassword());
		}
		
		
		
		return ResponseEntity.status(200).body(userRepository.save(updatedUser));
	}
}