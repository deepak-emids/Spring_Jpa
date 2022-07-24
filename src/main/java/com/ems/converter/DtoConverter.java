package com.ems.converter;

import com.ems.dto.EmployeeDto;
import com.ems.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {
    public EmployeeDto EmployeeToDto(Employee employee) {
        EmployeeDto emp = new EmployeeDto();

        emp.setEmail(employee.getEmail());
        emp.setPassword(employee.getPassword());

        return emp;
    }

    public Employee dtoToEmployee(EmployeeDto employee) {
        Employee emp = new Employee();
        emp.setEmail(employee.getEmail());
        emp.setPassword(employee.getPassword());

        return emp;
    }

    public List<EmployeeDto> EmployeeListToEmployeeDtoList(List<Employee> Employees) {
        List<EmployeeDto> EmployeeDtoList = Employees.stream().map(Employee -> EmployeeToDto(Employee))
                .collect(Collectors.toList());
        return EmployeeDtoList;
    }
}