package com.ems.controller;

import java.util.List;
import java.util.Optional;

import com.ems.converter.DtoConverter;
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
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employee) {
        Employee saved = employeeService.addEmployee(employee);
        EmployeeDto data = converter.EmployeeToDto(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = employeeService.getAllEmployee();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployees(@PathVariable("id") int id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employee, @PathVariable("id") int id) {
        Employee updated = employeeService.updateEmployee(employee, id);
        EmployeeDto data = converter.EmployeeToDto(updated);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployees(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted";
    }
}
