package com.cognixia.jump.todoproject.service;

import java.util.List;
import java.util.Optional;

import com.cognixia.jump.todoproject.exception.ResourceNotFoundException;
import com.cognixia.jump.todoproject.exception.SameInputException;
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

	public ResponseEntity<User> updateUser(int id, User updatedUser) throws SameInputException, ResourceNotFoundException {
		User currentUser = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("user with " + id + " does not exist")
		);

		if (updatedUser.getFirstName() == null
				|| updatedUser.getFirstName().length() < 1
				|| updatedUser.getFirstName().equals(currentUser.getFirstName())) {
			throw new SameInputException("first name");
		}

		if (updatedUser.getLastName() == null
				|| updatedUser.getLastName().length() < 1
				|| updatedUser.getLastName().equals(currentUser.getLastName())) {
			throw new SameInputException("last name");
		}

		if (updatedUser.getUsername() == null
				|| updatedUser.getUsername().length() < 1
				|| updatedUser.getUsername().equals(currentUser.getUsername())) {
			throw new SameInputException("username");
		}

		if (updatedUser.getPassword() == null
				|| updatedUser.getPassword().length() < 1
				|| updatedUser.getPassword().equals(currentUser.getPassword())) {
			throw new SameInputException("password");
		}

		return ResponseEntity.status(200).body(userRepository.save(updatedUser));
	}
}