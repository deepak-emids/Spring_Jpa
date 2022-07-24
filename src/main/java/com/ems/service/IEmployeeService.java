package com.ems.service;

import com.ems.dto.ResponseDto;
import com.ems.model.Employee;
import com.ems.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;


public interface IEmployeeService {

    ResponseDto addEmployee(EmployeeDto employee);

    ResponseDto getAllEmployee();

    ResponseDto getEmployee(int id);

    ResponseDto updateEmployee(EmployeeDto employee,int id);

    ResponseDto deleteEmployee(int id);

}

