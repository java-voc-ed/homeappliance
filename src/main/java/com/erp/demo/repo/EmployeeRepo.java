package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	Boolean existsByUsernameEquals(String username);

	Boolean existsByNationalIdEquals(String nationalId);

	Employee findByUsername(String username);

}
