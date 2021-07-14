package com.cognixia.jump.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.todoproject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
