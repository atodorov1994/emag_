package com.emag.controller;

import com.emag.model.User;
import com.emag.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User register(@RequestBody User u){
        userRepository.save(u);
        return u;
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return userRepository.findAll();
    }
}
