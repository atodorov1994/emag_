package com.emag.model.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditCategoryDTO {
    @NotBlank
    private String oldName;
    @NotBlank
    private String newName;
}
