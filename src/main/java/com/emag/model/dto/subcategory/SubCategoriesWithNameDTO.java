package com.emag.model.dto.subcategory;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SubCategoriesWithNameDTO {
    @NotBlank
    private String name;
}
