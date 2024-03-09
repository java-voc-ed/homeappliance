package com.erp.demo.controller.internal;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.Employee;
import com.erp.demo.model.physical.Member;
import com.erp.demo.service.internal.EmployeeMgmt;

@RestController
@RequestMapping("/api/in/v1/employees")
public class EmployeeMgmtController {
	
	@Autowired
	EmployeeMgmt employeeMgmt;
	
	/**
	 * CRUD Operation
	 */
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAll() {
		return ResponseEntity.ok().body(employeeMgmt.getAll());
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(employeeMgmt.getById(id));
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody Employee employee) {
		Optional<Employee> createdEmployee = employeeMgmt.create(employee);
		return (createdEmployee.isPresent())
				? ResponseEntity.created(URI.create("/api/in/v1/employees/" + createdEmployee.get().getEid())).build()
				: ResponseEntity.badRequest().body("Employee already exists.");
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
		return (id.equals(employee.getEid()) && employeeMgmt.update(employee).isPresent())
				? ResponseEntity.noContent().location(URI.create("/api/in/v1/employees/" + id)).build()
				: ResponseEntity.badRequest().body("Employee does not exist.");

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		Optional<Employee> deletedEmployee = employeeMgmt.delete(id);
		return (deletedEmployee.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("Employee does not exist.");
	}
	
	
	/**
	 * Form Pre-submission Validation
	 */
	
	@PostMapping(value = "/validate/username")
	public ResponseEntity<Boolean> isValidUsername(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeMgmt.isValidUsername(employee));
	}

	@PostMapping(value = "/validate/national-id")
	public ResponseEntity<Boolean> isValidNationalId(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeMgmt.isValidNationalId(employee));
	}
	
}
