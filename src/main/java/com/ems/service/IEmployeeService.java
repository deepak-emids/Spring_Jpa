package com.ems.service;

import com.ems.model.Employee;
import com.ems.dto.EmployeeDto;

import java.util.List;


public interface IEmployeeService {

    Employee addEmployee(EmployeeDto subject);

    List<Employee> getAllEmployee();

    Employee getEmployee(int id);

    Employee updateEmployee(EmployeeDto subject,int id);

    void deleteEmployee(int id);

}

