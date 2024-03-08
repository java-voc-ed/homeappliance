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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.Dispatchment;
import com.erp.demo.service.internal.DispatchmentMgmt;

@RestController
@RequestMapping("/api/in/v1/dispatchments")
public class DispatchmentMgmtController {

	@Autowired
	DispatchmentMgmt dispatchmentMgmt;
	
	@GetMapping
	public ResponseEntity<List<Dispatchment>> getAll() {
		return ResponseEntity.ok().body(dispatchmentMgmt.getAll());
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Dispatchment> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(dispatchmentMgmt.getById(id));
	}
	
	@GetMapping(value = "/query/oid")
	public ResponseEntity<Dispatchment> getByOid(@RequestParam("id") Integer oid) {
		return ResponseEntity.of(dispatchmentMgmt.getById(oid));
	}	
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody Dispatchment dispatchment) {
		Optional<Dispatchment> createdDispatchment = dispatchmentMgmt.create(dispatchment);
		return (createdDispatchment.isPresent())
				? ResponseEntity.created(URI.create("/api/in/v1/dispatchments/" + createdDispatchment.get().getDid())).build()
				: ResponseEntity.badRequest().body("Dispatchment already exists.");
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody Dispatchment dispatchment) {
		return (id.equalsIgnoreCase(dispatchment.getDid().toString()) && dispatchmentMgmt.update(dispatchment).isPresent())
				? ResponseEntity.noContent().location(URI.create("/api/in/v1/dispatchments/" + id)).build()
				: ResponseEntity.badRequest().body("Dispatchment does not exist.");

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		Optional<Dispatchment> deletedDispatchment = dispatchmentMgmt.delete(id);
		return (deletedDispatchment.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("Dispatchment does not exist.");
	}

}
