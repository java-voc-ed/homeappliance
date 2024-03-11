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

	public Optional<Member> getById(Integer id) {
		return (id.equals(getLoggedInMember().getMid()))
				? memberRepo.findById(id)
				: Optional.empty();
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
	
	private MemberUserDetails getLoggedInMember() {
		return (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
