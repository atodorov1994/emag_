package com.emag.model.dto.category;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryWithoutIdDTO {
    @NotBlank
    private String name;
}
