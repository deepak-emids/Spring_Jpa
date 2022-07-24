package com.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        responseDto.setId(saved.getId());
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployees(@PathVariable("id") int id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(@RequestBody EmployeeDto employee, @PathVariable("id") int id) {

        Employee updated = employeeService.updateEmployee(employee, id);

        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setName(updated.getEmail());
        responseDto.setPassword(updated.getPassword());
        responseDto.setId(updated.getId());
        return responseDto;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployees(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted";
    }
}
