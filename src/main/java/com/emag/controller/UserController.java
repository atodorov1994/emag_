package com.emag.controller;

import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.register.RegisterRequestUserDTO;
import com.emag.model.dto.register.RegisterResponseUserDTO;
import com.emag.model.dto.user.LoginRequestUserDTO;
import com.emag.model.dto.user.LogoutDTO;
import com.emag.model.dto.user.UserWithoutPasswordDTO;
import com.emag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private SessionManager sessionManager;


    @PostMapping("/users")
    public ResponseEntity<RegisterResponseUserDTO> register(@RequestBody RegisterRequestUserDTO u , HttpSession session){
        sessionManager.isLoggedVerification(session);
        return ResponseEntity.ok(userService.register(u));
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserWithoutPasswordDTO> login(@RequestBody LoginRequestUserDTO dto , HttpSession session){
        UserWithoutPasswordDTO user = userService.login(dto);
        sessionManager.loginUser(session , user.getId());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/logout")
    public ResponseEntity<LogoutDTO> logout(HttpSession session){
        sessionManager.logoutUser(session);
        return ResponseEntity.ok(new LogoutDTO("Logout successful!"));
    }

    @GetMapping("/users/{id}")
    public UserWithoutPasswordDTO getUserById(@PathVariable long id, HttpSession session) {
        if (!sessionManager.userHasPrivileges(session, id)) {
            throw new UnauthorizedException("No privileges!");
        }
        return userService.findById(id);
    }








//    @GetMapping("/users")
//    public List<User> getAll(){
//        return userRepository.findAll();
//    }
//


}
