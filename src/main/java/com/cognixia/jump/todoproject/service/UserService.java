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

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return ResponseEntity.status(200).body(userList);
    }

    public ResponseEntity<User> addUser(User user) {
        return ResponseEntity.status(201).body(userRepository.save(user));
    }

    public ResponseEntity<User> deleteUser(int id) {
        Optional<User> deletedUser = userRepository.findById(id);

        // check if the User we are deleting exists
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

        // FIRST NAME
        if (updatedUser.getFirstName() != null) {

            // check if first name is valid
            if (updatedUser.getFirstName().trim().length() < 1) {
                throw new IllegalArgumentException("Please enter a valid first name");

                // check if first name is equal to what already exists
            } else if (updatedUser.getFirstName().equals(currentUser.getFirstName())) {
                throw new SameInputException("first name");
            } else {

                // perform update
                currentUser.setFirstName(updatedUser.getFirstName());
            }
        } else {

            // if null then retrieve old info
            currentUser.setFirstName(currentUser.getFirstName());
        }

        // LAST NAME
        if (updatedUser.getLastName() != null) {

            // check if last name is valid
            if (updatedUser.getLastName().trim().length() < 1) {
                throw new IllegalArgumentException("Please enter a valid last name");

                // check if last name is equal to what already exists
            } else if (updatedUser.getLastName().equals(currentUser.getLastName())) {
                throw new SameInputException("last name");
            } else {

                // perform update
                currentUser.setLastName(updatedUser.getLastName());
            }
        } else {

            // if null then retrieve old info
            currentUser.setLastName(currentUser.getLastName());
        }

        // USERNAME
        if (updatedUser.getUsername() != null) {

            // check if username is valid
            if (updatedUser.getUsername().trim().length() < 1) {
                throw new IllegalArgumentException("Please enter a valid username");

                // check if username is equal to what already exists
            } else if (updatedUser.getUsername().equals(currentUser.getUsername())) {
                throw new SameInputException("username");
            } else {
                // perform update
                currentUser.setUsername(updatedUser.getUsername());
            }
        } else {

            // check if username doesn't already exist
            Optional<User> existingUser = userRepository.findByUsername(updatedUser.getUsername());
            if (existingUser.isEmpty()) {
                currentUser.setUsername(currentUser.getUsername());
            } else {
                throw new UsernameAlreadyExistsException("A username with the name " + updatedUser.getUsername() + " already exist. Please pick another username");
            }
        }

        // PASSWORD
        if (updatedUser.getPassword() != null) {

            // check if password is valid
            if (updatedUser.getPassword().trim().length() < 1) {
                throw new IllegalArgumentException("Please enter a valid password to update");

                // check if password is equal to what already exists
            } else if (updatedUser.getPassword().equals(currentUser.getPassword())) {
                throw new SameInputException("password");
            } else {

                // perform update
                currentUser.setPassword(updatedUser.getPassword());
            }
        } else {

            // if null then retrieve old info
            currentUser.setPassword(currentUser.getPassword());
        }

        return ResponseEntity.status(200).body(userRepository.save(currentUser));
    }
}