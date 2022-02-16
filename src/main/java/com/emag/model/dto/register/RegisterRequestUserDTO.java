package com.emag.model.dto.register;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequestUserDTO {
    @NotNull(message = "Email is mandatory!")
    @Email(message = "Invalid email format.")
    private String email;
    @NotNull(message = "Name is mandatory!")
    private String fullName;
    @NotNull(message = "Password is mandatory!")
    private String password;
    @NotNull(message = "Password confirmation is mandatory!")
    private String confirmPassword;
    private boolean isAdmin;
}
