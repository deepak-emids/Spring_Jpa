package com.ems.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmployeeException extends RuntimeException {
    String message;
    ExceptionType eType;

    public EmployeeException(String message, ExceptionType eType) {
        this.message = message;
        this.eType = eType;
    }
}
