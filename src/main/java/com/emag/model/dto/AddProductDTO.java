package com.emag.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddProductDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Brand is mandatory")
    private String brand;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Regular price should not be less than 1")
    private Double price;
    private String description;
    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity should not be less than 1")
    private Integer quantity;
    @Min(value = 1, message = "Warranty months should not be less than 1")
    private Integer warrantyMonths;
    @NotNull(message = "Subcategory id is mandatory")
    @Min(value = 1, message = "Category id should not be less than 1")
    private Integer subCategoryId;
}
