package com.ems.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ems.dto.ResponseDto;
import com.ems.exceptions.EmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dto.EmployeeDto;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    ResponseDto responseDto;

    @Override
    public ResponseDto addEmployee(EmployeeDto employee) {

        Employee emp = employeeRepository.findByEmail(employee.getEmail());

        if (emp != null) {
            throw new EmployeeException("Email Id already registered, Use Different Email Id.");
        } else {
            Employee s = new Employee();

            s.setEmail(employee.getEmail());
            s.setPassword(employee.getPassword());

            LocalDateTime createdAtTime = LocalDateTime.now();
            s.setCreatedAt(createdAtTime);
            s.setUpdatedAt(createdAtTime);
            Employee e = employeeRepository.save(s);
            responseDto.setData(e);
            return responseDto;
        }
    }

    public ResponseDto getAllEmployee() {
        List<Employee> employee = new ArrayList<>();
        employeeRepository.findAll().forEach(employee::add);

        responseDto.setData(employee);
        responseDto.setStatus(200);
        responseDto.setMessage("Employees Fetched");

        return responseDto;
    }

    @Override
    public ResponseDto getEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        responseDto.setData(employee);
        responseDto.setMessage("Employee Fetched");
        responseDto.setStatus(200);
        return responseDto;
    }

    @Override
    public ResponseDto updateEmployee(EmployeeDto emp, int id) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setEmail(emp.getEmail());
        employee.setPassword(emp.getPassword());

        Employee e = employeeRepository.save(employee);
        responseDto.setData(e);
        responseDto.setMessage("Employee Updated");
        responseDto.setStatus(200);
        return responseDto;
    }

    @Override
    public ResponseDto deleteEmployee(int id) {
        employeeRepository.deleteById(id);
        responseDto.setMessage("Employee Deleted");
        responseDto.setStatus(200);

        return responseDto;
    }
}
