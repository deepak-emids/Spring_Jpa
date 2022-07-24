package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ems.model.Employee;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
}
