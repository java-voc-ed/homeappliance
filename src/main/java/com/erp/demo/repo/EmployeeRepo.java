package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
