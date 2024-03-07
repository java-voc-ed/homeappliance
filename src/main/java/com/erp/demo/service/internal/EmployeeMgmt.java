package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.Employee;
import com.erp.demo.repo.EmployeeRepo;

@Service
public class EmployeeMgmt {

	@Autowired
	EmployeeRepo employeeRepo;
	
	/**
	 * CRUD Operation
	 */
	
	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}
	
	public Optional<Employee> getById(String id) {
		return employeeRepo.findById(Integer.valueOf(id));
	}

	public Optional<Employee> create(Employee employee) {
		return (!employeeRepo.existsByNationalIdEquals(employee.getNationalId())
				&& !employeeRepo.existsByUsernameEquals(employee.getUsername()))
				? Optional.of(employeeRepo.save(employee))
				: Optional.empty();
	}
	
	public Optional<Employee> update(Employee employee) {
		return  (employeeRepo.existsById(employee.getEid()))
				? Optional.of(employeeRepo.save(employee))
				: Optional.empty();
	}
	
	public Optional<Employee> delete(String id) {
		employeeRepo.deleteById(Integer.valueOf(id));
		return employeeRepo.findById(Integer.valueOf(id));		
	}
	
	/**
	 * Duplication Check
	 */

	public Boolean hasDuplicateUsername(Employee employee) {
		return employeeRepo.existsByUsernameEquals(employee.getUsername());
	}
	
	public Boolean hasDuplicateNationalId(Employee employee) {
		return employeeRepo.existsByNationalIdEquals(employee.getNationalId());
	}

}
