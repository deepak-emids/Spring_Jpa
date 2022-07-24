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

        Employee foundEmployee = employeeRepository.findByEmail(employee.getEmail());

        if (foundEmployee != null) {
            throw new EmployeeException("Email Id already registered, Use Different Email Id.");
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
            responseDto.setData(employee);
            responseDto.setStatus(404);
            responseDto.setMessage("Employees Not Found");

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
        if (employee == null) {
            responseDto.setData(employee);
            responseDto.setMessage("Employee Not Found");
            responseDto.setStatus(404);
        } else {
            responseDto.setData(employee);
            responseDto.setMessage("Employee Found");
            responseDto.setStatus(200);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateEmployee(EmployeeDto emp, int id) {
        Employee employee = employeeRepository.findById(id).get();

        if (employee != null) {
            employee.setEmail(emp.getEmail());
            employee.setPassword(emp.getPassword());

            Employee updated = employeeRepository.save(employee);
            responseDto.setData(updated);
            responseDto.setMessage("Employee Updated");
            responseDto.setStatus(200);
        } else {
            responseDto.setMessage("Employee Not Found");
            responseDto.setStatus(404);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id).get();

        if (employee != null) {
            employeeRepository.deleteById(id);
            responseDto.setMessage("Employee Deleted");
            responseDto.setStatus(200);

        }else {
            responseDto.setMessage("Employee Not Found");
            responseDto.setStatus(404);
        }
        return responseDto;
    }
}
