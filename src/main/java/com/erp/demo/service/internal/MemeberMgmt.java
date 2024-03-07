package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.Member;
import com.erp.demo.repo.MemberRepo;

@Service
public class MemeberMgmt {

	@Autowired
	MemberRepo memberRepo;
	
	/**
	 * CRUD Operation
	 */
	
	public List<Member> getAll() {
		return memberRepo.findAll();
	}
	
	public Optional<Member> getById(String id) {
		return memberRepo.findById(Integer.valueOf(id));
	}

	public Optional<Member> create(Member member) {
		return (!memberRepo.existsById(member.getMid()))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Member> update(Member member) {
		return  (memberRepo.existsById(member.getMid()))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Member> delete(String id) {
		memberRepo.deleteById(Integer.valueOf(id));
		return memberRepo.findById(Integer.valueOf(id));		
	}
	
	/**
	 * Duplication Check
	 */

	public Boolean hasDuplicateUsername(Member member) {
		return memberRepo.existsByUsernameEquals(member.getUsername());
	}
	
	public Boolean hasDuplicateNationalId(Member member) {
		return memberRepo.existsByNationalId(member.getNationalId());
	}
	
}
