package com.emag.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditPasswordRequestDTO {
    @NotNull(message = "Field old password is mandatory!")
    private String oldPassword;
    @NotNull(message = "Field new password is mandatory!")
    private String newPassword;
    @NotNull(message = "Field confirm new password is mandatory!")
    private String confirmNewPassword;

}
