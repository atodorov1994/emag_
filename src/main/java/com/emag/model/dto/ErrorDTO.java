package com.emag.model.dto;


import lombok.Data;

@Data
public class ErrorDTO {

    private String message;
    private int status;
}
