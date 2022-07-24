package com.ems.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.EmployeeDto;
import com.ems.dto.EmployeeResponseDto;
import com.ems.model.Employee;
import com.ems.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public EmployeeResponseDto addEmployee(@RequestBody EmployeeDto employee) {

        Employee saved = employeeService.addEmployee(employee);

        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setName(saved.getEmail());
        responseDto.setPassword(saved.getPassword());
        return responseDto;
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = employeeService.getAllEmployee();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
}
