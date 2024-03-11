package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.Member;
import com.erp.demo.repo.MemberRepo;

@Service
public class MemberMgmt {

	@Autowired
	MemberRepo memberRepo;
	
	/**
	 * CRUD Operation
	 */
	
	public List<Member> getAll() {
		return memberRepo.findAll();
	}
	
	public Optional<Member> getById(Integer id) {
		return memberRepo.findById(id);
	}

	public Optional<Member> create(Member member) {
		return (!memberRepo.existsByNationalIdEquals(member.getNationalId())
				&& !memberRepo.existsByUsernameEquals(member.getUsername()))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Member> update(Member member) {
		return  (memberRepo.existsById(member.getMid()))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Member> delete(Integer id) {
		memberRepo.deleteById(id);
		return memberRepo.findById(id);		
	}
	
	/**
	 * Validation
	 */

	public Boolean isValidUsername(Member member) {
		// TODO: 可在這邊納入其他驗證規則。
		return !memberRepo.existsByUsernameEquals(member.getUsername());
	}
	
	public Boolean isValidNationalId(Member member) {
		// TODO: 可在這邊納入其他驗證規則。
		return !memberRepo.existsByNationalIdEquals(member.getNationalId());
	}
	
}
