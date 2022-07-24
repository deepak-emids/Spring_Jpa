package com.ems.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleEmployeeException(EmployeeException employeeException) {
        return new ResponseEntity<String>(employeeException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
