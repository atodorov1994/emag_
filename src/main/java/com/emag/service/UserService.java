package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.model.dto.register.RegisterRequestUserDTO;
import com.emag.model.dto.register.RegisterResponseUserDTO;
import com.emag.model.pojo.User;
import com.emag.util.UserUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserService extends AbstractService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterResponseUserDTO register(RegisterRequestUserDTO u){
        String email = u.getEmail().trim();
        if (userRepository.findByEmail(email) != null){
            throw new BadRequestException("Email already exists!");
        }
        if (!UserUtility.isValidEmail(email)){
            throw new BadRequestException("Invalid email!");
        }
        String password = u.getPassword();
        if (!UserUtility.isValidPass(password)){
            throw new BadRequestException("Invalid password!");
        }
        if (!UserUtility.passwordsMatch(u)){
            throw new BadRequestException("Passwords mismatch!");
        }
        if (!UserUtility.isValidName(u.getFullName())){
            throw new BadRequestException("Invalid name format!");
        }
        String encodedPass = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPass);
        user.setFullName(u.getFullName());
        user.setAdmin(u.isAdmin());
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user = userRepository.save(user);
        return modelMapper.map(user , RegisterResponseUserDTO.class);
    }

}
