package com.emag.model.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequestUserDTO {
    @NotNull(message = "Email is mandatory!")
    @Email(message = "Invalid email format.")
    private String email;
    @NotNull(message = "Password is mandatory!")
    private String password;
}
