package com.emag.controller;

import com.emag.exception.AuthenticationException;
import com.emag.exception.BadRequestException;
import com.emag.exception.NotFoundException;
import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorDTO handleUnauthorized(Exception e){
        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(e.getMessage());
        dto.setStatus(HttpStatus.UNAUTHORIZED.value());
        dto.setTime(LocalDateTime.now());
        return dto;
    }
    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleBadRequest(Exception e){
        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(e.getMessage());
        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setTime(LocalDateTime.now());
        return dto;
    }
    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handleNotFound(Exception e){
        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(e.getMessage());
        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setTime(LocalDateTime.now());
        e.printStackTrace();
        return dto;
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorDTO handleAuthentication(Exception e){
        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(e.getMessage());
        dto.setStatus(HttpStatus.UNAUTHORIZED.value());
        dto.setTime(LocalDateTime.now());
        return dto;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handleAllOthers(Exception e){
        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(e.getMessage());
        dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dto.setTime(LocalDateTime.now());
        e.printStackTrace();
        return dto;
    }
}
