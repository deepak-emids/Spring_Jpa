package com.ems.exceptions;

public class EmployeeException extends RuntimeException {

    String message;
    ExceptionType eType;

    public EmployeeException(String message, ExceptionType eType) {
        this.message = message;
        this.eType = eType;
    }
}
