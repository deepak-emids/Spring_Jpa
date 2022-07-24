package com.ems.exceptions;

import com.ems.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ExceptionDto> handleEmployeeException(EmployeeException e, WebRequest request) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }
}
