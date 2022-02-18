package com.emag.model.dto.subcategory;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddSubCategoryDTO {
    @NotBlank
    private String subCategoryName;
    @NotBlank
    private String categoryName;
}
