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
	
	public Optional<Employee> getById(Integer id) {
		return employeeRepo.findById(id);
	}

	public Optional<Employee> create(Employee employee) {
		employee.setEid(null);
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
	
	public Optional<Employee> delete(Integer id) {
		employeeRepo.deleteById(id);
		return employeeRepo.findById(id);		
	}
	
	/**
	 * Validation
	 */

	public Boolean isValidUsername(Employee employee) {
		// TODO: 可在這邊納入其他驗證規則。
		return employeeRepo.existsByUsernameEquals(employee.getUsername());
	}
	
	public Boolean isValidNationalId(Employee employee) {
		// TODO: 可在這邊納入其他驗證規則。
		return employeeRepo.existsByNationalIdEquals(employee.getNationalId());
	}

}
