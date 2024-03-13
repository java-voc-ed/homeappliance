package com.erp.demo.controller.external;

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
import com.erp.demo.service.external.MemberSvc;

@RestController
@RequestMapping("/api/ex/v1/members")
public class MemberSvcController {
	
	@Autowired
	MemberSvc memberSvc;
	
	/**
	 * CRUD Operation
	 */
	
	@GetMapping
	public ResponseEntity<Member> getSelf() {
		return ResponseEntity.of(memberSvc.getSelf());
	}
	
	@PostMapping
	public ResponseEntity<String> register(@RequestBody Member member) {
		Optional<Member> createdMember = memberSvc.register(member);
		return (createdMember.isPresent())
				? ResponseEntity.created(URI.create("/api/ex/v1/members" + createdMember.get().getMid())).build()
				: ResponseEntity.badRequest().body("Member already exists.");
	}
	
	@PutMapping
	public ResponseEntity<String> updateSelf(@RequestBody Member member) {
		return (memberSvc.updateSelf(member).isPresent())
				? ResponseEntity.noContent().location(URI.create("/api/ex/v1/members")).build()
				: ResponseEntity.badRequest().body("Member does not exist.");

	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteSelf() {
		Optional<Member> deletedMember = memberSvc.deleteSelf();
		return (deletedMember.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("Member does not exist.");
	}
	
	
	/**
	 * Form Pre-submission Validation
	 */
	
	@PostMapping(value = "/validate/username")
	public ResponseEntity<Boolean> isValidUsername(@RequestBody Member member) {
		return ResponseEntity.ok(memberSvc.isValidUsername(member));
	}

	@PostMapping(value = "/validate/national-id")
	public ResponseEntity<Boolean> isValidNationalId(@RequestBody Member member) {
		return ResponseEntity.ok(memberSvc.isValidNationalId(member));
	}
	
}
