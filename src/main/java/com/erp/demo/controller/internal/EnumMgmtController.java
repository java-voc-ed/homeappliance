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

import com.erp.demo.model.physical.PostalCode;
import com.erp.demo.service.external.EnumSvc;

@RestController
@RequestMapping(value = "/api/in/v1/enum")
public class EnumMgmtController {

	@Autowired
	private EnumSvc enumSvc;
	
	@GetMapping(value = "/postal-code")
	public ResponseEntity<List<PostalCode>> getAllPostalCode() {
		return ResponseEntity.ok(enumSvc.getAllPostalCodes());
	}
	
	@GetMapping(value = "/postal-code/{id}")
	public ResponseEntity<PostalCode> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(enumSvc.getPostalCodeById(id));
	}
	
	@PostMapping(value = "/postal-code/add")
	public ResponseEntity<String> add(@RequestBody PostalCode postalCode) {
		Optional<PostalCode> createdPostalCode = enumSvc.createPostalCode(postalCode);
		return (createdPostalCode.isPresent())
				? ResponseEntity.created(URI.create("/api/in/v1/enum/postal-code/" + createdPostalCode.get().getPostalCode())).build()
				: ResponseEntity.badRequest().body("PostalCode already exists.");
	}
	
	@PutMapping(value = "/postal-code/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody PostalCode postalCode) {
		Optional<PostalCode> updatedPostalCode = enumSvc.updatePostalCode(postalCode);
		return (updatedPostalCode.isPresent())
				? ResponseEntity.noContent().location(URI.create("/api/in/v1/enum/postal-code/" 
																+ updatedPostalCode.get().getPostalCode())).build()
				: ResponseEntity.badRequest().body("PostalCode does not exist.");

	}
	
	@DeleteMapping(value = "/postal-code/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		Optional<PostalCode> deletedPostalCode = enumSvc.deletePostalCode(id);
		return (deletedPostalCode.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("PostalCode does not exist.");
	}
	
}
