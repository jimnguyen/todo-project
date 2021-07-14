package com.cognixia.jump.todoproject.config;

import com.cognixia.jump.todoproject.model.ToDo;
import com.cognixia.jump.todoproject.repository.ToDoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class ToDoConfig {

    @Bean
    CommandLineRunner commandLineRunner(ToDoRepository toDoRepository) {
        return args -> {
            ToDo todo = new ToDo(null, "do laundry", new Date());
            ToDo todo2 = new ToDo(null, "buy groceries", new Date());
            toDoRepository.saveAll(List.of(todo, todo2));
        };
    }
}
