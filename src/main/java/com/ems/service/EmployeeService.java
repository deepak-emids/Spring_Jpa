package com.ems.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ems.dto.ResponseDto;
import com.ems.exceptions.EmployeeException;
import com.ems.exceptions.ExceptionType;
import com.ems.exceptions.GlobalExceptionsHandler;
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

        Employee foundEmployee = employeeRepository.findByEmail(employee.getEmail());

        if (foundEmployee != null) {
            throw new EmployeeException("Email Id already registered, Use Different Email Id.", ExceptionType.CONFLICT);
        } else {
            Employee emp = new Employee();

            emp.setEmail(employee.getEmail());
            emp.setPassword(employee.getPassword());

            LocalDateTime createdAtTime = LocalDateTime.now();
            emp.setCreatedAt(createdAtTime);
            emp.setUpdatedAt(createdAtTime);

            Employee saved = employeeRepository.save(emp);
            responseDto.setData(saved);
            responseDto.setStatus(200);
            responseDto.setMessage("Employees Registered");
            return responseDto;
        }
    }

    public ResponseDto getAllEmployee() {
        List<Employee> employee = new ArrayList<>();
        employeeRepository.findAll().forEach(employee::add);

        if (employee.size() == 0) {
            throw new EmployeeException("Employees Not Found", ExceptionType.EMPLOYEE_NOT_FOUND);
        } else {
            responseDto.setData(employee);
            responseDto.setStatus(200);
            responseDto.setMessage("Employees Fetched");
        }
        return responseDto;
    }

    @Override
    public ResponseDto getEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            System.out.println("hi");
            throw new EmployeeException("Employee Not Found", ExceptionType.EMPLOYEE_NOT_FOUND);
        } else {
            responseDto.setData(employee);
            responseDto.setMessage("Employee Found");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateEmployee(EmployeeDto emp, int id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            throw new EmployeeException("Employee Not Found", ExceptionType.EMPLOYEE_NOT_FOUND);
        } else {
            employee.get().setEmail(emp.getEmail());
            employee.get().setPassword(emp.getPassword());

            Employee updated = employeeRepository.save(employee.get());
            responseDto.setData(updated);
            responseDto.setMessage("Employee Updated");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        System.out.println(employee);
        if (employee.isEmpty()) {
            throw new EmployeeException("Employee Not Found", ExceptionType.EMPLOYEE_NOT_FOUND);
        } else {
            employeeRepository.deleteById(id);
            responseDto.setData("");
            responseDto.setMessage("Employee Deleted");
            responseDto.setStatus(200);
        }
        return responseDto;
    }
}
