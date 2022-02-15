package com.emag.controller;

import com.emag.model.dto.register.RegisterRequestUserDTO;
import com.emag.model.dto.register.RegisterResponseUserDTO;
import com.emag.model.pojo.User;
import com.emag.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private SessionManager sessionManager;


    @PostMapping("/users")
    public ResponseEntity<RegisterResponseUserDTO> register(@RequestBody RegisterRequestUserDTO u , HttpSession session){
//        sessionManager.isLoggedVerification(session);
        return ResponseEntity.ok(userService.register(u));
    }


//    @GetMapping("/users")
//    public List<User> getAll(){
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/users/{id}")
//    public User getUserById(@PathVariable long id) {
//        return userRepository.findById(id).orElse(null);
//    }


}
