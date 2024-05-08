package com.ecommerce.UserService.Exceptions;

import com.ecommerce.UserService.DTO.ExceptionDto;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(UserExists.class)
    public ResponseEntity<ExceptionDto> UserExists(UserExists ex) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.FOUND);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ExceptionDto> UserNotFound(UserNotFound ex) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
