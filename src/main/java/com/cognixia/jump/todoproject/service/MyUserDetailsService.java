package com.cognixia.jump.todoproject.service;

import java.util.Optional;

import com.cognixia.jump.todoproject.model.User;
import com.cognixia.jump.todoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userFound = userRepository.findByUsername(username);

        if(userFound.isEmpty()) {
            throw new UsernameNotFoundException("No user with username: " + username);
        }

        return new MyUserDetails(userFound.get());
    }
}
