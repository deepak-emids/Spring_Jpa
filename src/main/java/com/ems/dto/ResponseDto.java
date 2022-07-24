package com.ems.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
public class ResponseDto {
    private Integer status;
    private Object data;
    private Object error;
    private Object message;
}
