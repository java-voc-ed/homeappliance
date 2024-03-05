package com.erp.demo.controller;

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

import com.erp.demo.dao.EmployeeDAO;
import com.erp.demo.model.Employee;



@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;

	// http://localhost:8080/employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}

	// http://localhost:8080/employee/{id}
	@GetMapping("/employee/{id}")
	public Optional<Employee> findCustomerById(@PathVariable("id") Integer id) {
		return employeeDAO.findById(id);
	}

	@PostMapping(value = "/employee")
	public List<Employee> addOneEmployee(@RequestBody Employee employee) {
		employeeDAO.save(employee);
		return getAllEmployees();
	}
	
	@PutMapping("/employee/{id}")
	public Optional<Employee> updatEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
		
		Optional<Employee> findEmployee = employeeDAO.findById(id);
		
		if (findEmployee != null) {
			employeeDAO.save(employee);
		}
		
		return findEmployee;
	}
	
	@DeleteMapping("/employee/{id}")
	public List<Employee> removeOneEmployee(@PathVariable("id") Integer id) {
		
		Optional<Employee> findEmployee = employeeDAO.findById(id);
		
		if (findEmployee != null) {
			employeeDAO.deleteById(id);
		}
		return getAllEmployees();
	}
	
}
