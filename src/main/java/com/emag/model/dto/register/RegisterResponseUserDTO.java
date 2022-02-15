package com.emag.model.dto.register;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegisterResponseUserDTO {
    private long id;
    private String email;
    private String fullName;
    private boolean isAdmin;
}
