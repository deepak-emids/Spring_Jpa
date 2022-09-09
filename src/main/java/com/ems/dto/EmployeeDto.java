package com.ems.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class EmployeeDto {
    @Email
    @NotNull
    public String email;

    @NotNull
    @Max(6)
    @Min(4)
    public String password;
}
