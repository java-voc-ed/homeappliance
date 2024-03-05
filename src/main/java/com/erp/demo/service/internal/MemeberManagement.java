package com.erp.demo.service.internal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.dao.MemberDAO;
import com.erp.demo.model.Member;

@Service
public class MemeberManagement {

	@Autowired
	MemberDAO memberDao;
	
	public Optional<Member> getById(Integer id) {
//		return memberDao.findById(id);
//		Member member = new Member();
//		member.setId(100);
//		member.setName("Alice");
//		member.setAddress("Taipei");
//		return Optional.of(member);
		return Optional.empty();
	}

}
