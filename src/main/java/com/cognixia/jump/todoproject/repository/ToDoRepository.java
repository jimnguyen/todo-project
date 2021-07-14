package com.cognixia.jump.todoproject.repository;

import com.cognixia.jump.todoproject.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

}
