package com.ems.controller;

import java.util.List;
import java.util.Optional;

import com.ems.converter.DtoConverter;
import com.ems.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ems.dto.EmployeeDto;
import com.ems.model.Employee;
import com.ems.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DtoConverter converter;

    @PostMapping()
    public ResponseDto addEmployee(@RequestBody EmployeeDto employee) {
        ResponseDto saved = employeeService.addEmployee(employee);
        return saved;
    }

    @GetMapping()
    public ResponseDto getAllEmployees() {
        ResponseDto list = employeeService.getAllEmployee();
        return list;
    }

    @GetMapping("/{id}")
    public ResponseDto getEmployees(@PathVariable("id") int id) {
        ResponseDto employee = employeeService.getEmployee(id);
        return employee;
    }

    @PutMapping("/{id}")
    public ResponseDto updateEmployee(@RequestBody EmployeeDto employee, @PathVariable("id") int id) {
        ResponseDto updated = employeeService.updateEmployee(employee, id);

        return updated;
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteEmployees(@PathVariable("id") int id) {
        ResponseDto delete = employeeService.deleteEmployee(id);
        return delete;
    }
}
