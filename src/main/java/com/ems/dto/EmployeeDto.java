package com.ems.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import java.util.Set;

@Getter
@Setter
@Data
public class EmployeeDto {
    public int id;
    public String email;
    public String password;
}
