package com.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dto.EmployeeDto;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    public EmployeeRepository employeeRepo;

    @Override
    public Employee addEmployee(EmployeeDto employee) {
        // TODO Auto-generated method stub
        Employee s = new Employee();

        s.setEmail(employee.getEmail());
        s.setPassword(employee.getPassword());
        return employeeRepo.save(s);
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employee = new ArrayList<>();
        employeeRepo.findAll().forEach(employee::add);
        return employee;
    }

    @Override
    public Employee getEmployee(int id) {
        return null;
    }

    @Override
    public Employee updateEmployee(EmployeeDto subject,int id) {
        return null;
    }

    @Override
    public void deleteEmployee(int id) {

    }
}
