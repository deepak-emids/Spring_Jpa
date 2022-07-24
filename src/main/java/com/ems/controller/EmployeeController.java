package com.ems.controller;

import com.ems.converter.DtoConverter;
import com.ems.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ems.dto.EmployeeDto;
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
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employee) {
        ResponseDto result = employeeService.addEmployee(employee);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping()
    public ResponseEntity getAllEmployees() {
        ResponseDto result = employeeService.getAllEmployee();
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployees(@PathVariable("id") int id) {
        ResponseDto result = employeeService.getEmployee(id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto employee, @PathVariable("id") int id) {
        ResponseDto result = employeeService.updateEmployee(employee, id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployees(@PathVariable("id") int id) {
        ResponseDto result = employeeService.deleteEmployee(id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
