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

import com.erp.demo.model.physical.Member;
import com.erp.demo.service.internal.MemberMgmt;

@RestController
@RequestMapping("/api/in/v1/members")
public class MemberMgmtController {
	
	@Autowired
	MemberMgmt memberMgmt;
	
	/**
	 * CRUD Operation
	 */
	
	@GetMapping
	public ResponseEntity<List<Member>> getAll() {
		return ResponseEntity.ok().body(memberMgmt.getAll());
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Member> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(memberMgmt.getById(id));
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody Member member) {
		Optional<Member> createdMember = memberMgmt.create(member);
		return (createdMember.isPresent())
				? ResponseEntity.created(URI.create("/api/in/v1/members/" + createdMember.get().getMid())).build()
				: ResponseEntity.badRequest().body("Member already exists.");
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody Member member) {
		return (id.equals(member.getMid()) && memberMgmt.update(member).isPresent())
				? ResponseEntity.noContent().location(URI.create("/api/in/v1/members/" + id)).build()
				: ResponseEntity.badRequest().body("Member does not exist.");

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		Optional<Member> deletedMember = memberMgmt.delete(id);
		return (deletedMember.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("Member does not exist.");
	}
	
	
	/**
	 * Form Pre-submission Validation
	 */
	
	@PostMapping(value = "/validate/username")
	public ResponseEntity<Boolean> isValidUsername(@RequestBody Member member) {
		return ResponseEntity.ok(memberMgmt.isValidUsername(member));
	}

	@PostMapping(value = "/validate/national-id")
	public ResponseEntity<Boolean> isValidNationalId(@RequestBody Member member) {
		return ResponseEntity.ok(memberMgmt.isValidNationalId(member));
	}
	
}
