package com.emag.model.dto.review;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class DoReviewDTO {
    @Min(value = 1, message = "Product id should not be less than 1")
    private Long productId;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @Min(value = 1, message = "Review rating should not be less than 1")
    @Max(value = 5, message = "Review rating should not be greater than 5")
    private Integer rating;
}
