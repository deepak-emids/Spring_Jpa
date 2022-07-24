package com.ems.service;

import com.ems.dto.ResponseDto;
import com.ems.model.Employee;
import com.ems.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;


public interface IEmployeeService {

    ResponseDto addEmployee(EmployeeDto subject);

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployee(int id);

    Employee updateEmployee(EmployeeDto subject,int id);

    void deleteEmployee(int id);

}

