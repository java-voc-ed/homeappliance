package com.erp.demo.service.external;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.demo.model.authentication.MemberUserDetails;
import com.erp.demo.model.physical.Member;
import com.erp.demo.repo.MemberRepo;

/**
 * TODO: VALIDATE AGAINST SECURITY CONTEXT!!!!
 */

@Service
public class MemberSvc {

	@Autowired
	MemberRepo memberRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * CRUD Operation
	 */

	public Optional<Member> getSelf() {
		return memberRepo.findById(getLoggedInMember().getMid());
	}

	public Optional<Member> create(Member member) {
		member.setMid(null);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		return (!memberRepo.existsByNationalIdEquals(member.getNationalId())
				&& !memberRepo.existsByUsernameEquals(member.getUsername()))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Member> update(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		return  (member.getUsername().equals(getLoggedInMember().getUsername()))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Member> delete() {
		memberRepo.deleteById(getLoggedInMember().getMid());
		return memberRepo.findById(getLoggedInMember().getMid());		
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
	
	private MemberUserDetails getLoggedInMember() {
		return (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
