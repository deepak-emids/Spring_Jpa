package com.ems.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
@Slf4j
@Getter
@Setter
public class ExceptionDto {
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionDto(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
