package com.cognixia.jump.todoproject.controller;

import com.cognixia.jump.todoproject.exception.ResourceNotFoundException;
import com.cognixia.jump.todoproject.exception.SameInputException;
import com.cognixia.jump.todoproject.model.User;
import com.cognixia.jump.todoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping(path = "/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,
                                           @Valid @RequestBody User updatedUser) throws SameInputException, ResourceNotFoundException {
        return userService.updateUser(id, updatedUser);
    }
}
