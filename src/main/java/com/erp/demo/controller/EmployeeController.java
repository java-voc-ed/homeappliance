package com.erp.demo.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.Employee;
import com.erp.demo.repo.EmployeeRepo;



//@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepo employeeRepo;

	// http://localhost:8080/employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	// http://localhost:8080/employee/{eId}
	@GetMapping("/employee/{eId}")
	public Optional<Employee> findCustomerById(@PathVariable("eId") Integer eId) {
		return employeeRepo.findById(eId);
	}

	@PostMapping(value = "/employee")
	public List<Employee> addOneEmployee(@RequestBody Employee employee) {
		
		// 非理想的寫法...
		Timestamp timeStamp = Timestamp.from(Instant.now());
		
		if (employee.getCreateTime()==null) {
			employee.setCreateTime(timeStamp);
		}
		
		if (employee.getUpdateTime()==null) {
			employee.setUpdateTime(timeStamp);
		}
		
		employeeRepo.save(employee);
		return getAllEmployees();
	}
	
	@PutMapping("/employee/{eId}")
	public Optional<Employee> updatEmployee(@PathVariable("eId") Integer eid, @RequestBody Employee employee) {
		
		Optional<Employee> findEmployee = employeeRepo.findById(eid);
		
		if (findEmployee != null) {
			// 非理想的寫法...
			Timestamp timeStamp = Timestamp.from(Instant.now());
			employee.setUpdateTime(timeStamp);
			employeeRepo.save(employee);
		}
		
		return findEmployee;
	}
	
	@DeleteMapping("/employee/{eid}")
	public List<Employee> removeOneEmployee(@PathVariable("eid") Integer eid) {
		
		Optional<Employee> findEmployee = employeeRepo.findById(eid);
		
		if (findEmployee != null) {
			employeeRepo.deleteById(eid);
		}
		return getAllEmployees();
	}
	
}
