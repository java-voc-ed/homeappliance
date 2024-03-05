package com.erp.demo.controller.internal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.Member;
import com.erp.demo.service.internal.MemeberManagement;

@RestController
@RequestMapping("/members")
public class MemberManagementController {
	
	@Autowired
	MemeberManagement memberManagement;
	
	@GetMapping
	public ResponseEntity<Member> getById(@RequestParam("id") Integer id) {
		return ResponseEntity.of(memberManagement.getById(id));
	}
	
	@PostMapping
	public ResponseEntity add() {
		return ResponseEntity.accepted().build();
	} 
	
}
