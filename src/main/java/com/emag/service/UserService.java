package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.exception.MethodNotFoundException;
import com.emag.model.dto.register.RegisterRequestUserDTO;
import com.emag.model.dto.register.RegisterResponseUserDTO;
import com.emag.model.dto.user.LoginRequestUserDTO;
import com.emag.model.dto.user.UserWithoutPasswordDTO;
import com.emag.model.pojo.User;
import com.emag.util.UserUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserService extends AbstractService{


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

    public UserWithoutPasswordDTO login(LoginRequestUserDTO dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        User userFromDb = userRepository.findByEmail(email);
        if (userFromDb == null) {
            throw new MethodNotFoundException("Wrong email!");
        }
        String passwordFromDb = userFromDb.getPassword();
        if (!passwordEncoder.matches(password, passwordFromDb)) {
            throw new MethodNotFoundException("Wrong password!");
        }
        return modelMapper.map(userFromDb , UserWithoutPasswordDTO.class);
    }


    public UserWithoutPasswordDTO findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new BadRequestException("User does not exist!");
        }
        return modelMapper.map(user , UserWithoutPasswordDTO.class);
    }


}
