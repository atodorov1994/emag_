package com.emag.model.dto.product;

import com.emag.model.pojo.SubCategory;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class RequestProductDTO {
    @NotNull(message = "Product name is mandatory!")
    private String name;
    @NotNull(message = "Product sub category is mandatory!")
    @Column(name = "sub_category_id")
    private long subCategoryId;
    @NotNull(message = "Product brand is mandatory!")
    private String brand;
    @NotNull(message = "Product model is mandatory!")
    private String model;
    @NotNull(message = "Product price is mandatory!")
    private double price;
    @NotNull(message = "Product quantity is mandatory!")
    private int quantity;

    private String description;
    private int warrantyMonths;
}
