package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.erp.demo.model.Member;
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
	
	public Optional<Member> getById(Integer id) {
		return memberRepo.findById(String.valueOf(id));
	}

	public Optional<Member> create(Member member) {
		return (!memberRepo.existsById(String.valueOf(member.getId())))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Member> update(Member member) {
		return  (memberRepo.existsById(String.valueOf(member.getId())))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Member> delete(Integer id) {
		memberRepo.deleteById(String.valueOf(id));
		return memberRepo.findById(String.valueOf(id));		
	}
	
	/**
	 * Duplication Check
	 */

	public Boolean hasDuplicateUsername(Member member) {
		return memberRepo.existsByUsernameEquals(member.getUserName());
	}
	
	public Boolean hasDuplicateNationalId(Member member) {
		return memberRepo.existsbyNationalId(member.getNationalld());
	}
	
}
