package com.emag.exceptions;

public class MethodNotFoundException extends RuntimeException{
    public MethodNotFoundException(String message){
        super(message);
    }
}
