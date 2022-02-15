package com.emag.model.dto.user;

import com.emag.model.pojo.UserImage;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;

@Component
@Data
public class UserWithoutPasswordDTO {
    private long id;
    private String nickname;
    private String email;
    private String fullName;
    private Timestamp createdAt;
    private UserImage image;
    private String mobilePhone;
    private boolean isAdmin;
    private LocalDate birthDate;
    private String gender;



}
