package com.cognixia.jump.todoproject.config;

import com.cognixia.jump.todoproject.model.ToDo;
import com.cognixia.jump.todoproject.model.User;
import com.cognixia.jump.todoproject.repository.ToDoRepository;
import com.cognixia.jump.todoproject.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class ToDoConfig {

    @Bean
    CommandLineRunner commandLineRunner(ToDoRepository toDoRepository, UserRepository userRepository ) {
        return args -> {
        	User u1 = new User(null, "Jim", "N", "JimN", "12345", new ArrayList<>());
        	User u2 = new User(null, "Kia", "Mokhtari", "KiaM", "12345", new ArrayList<>());
        	
            ToDo todo = new ToDo(null, "do laundry", new Date(), u1);
            ToDo todo2 = new ToDo(null, "buy groceries", new Date(), u2);
            userRepository.saveAll(List.of(u1, u2));
            toDoRepository.saveAll(List.of(todo, todo2));
            
        };
    }
}
