package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.Member;

public interface MemberRepo extends JpaRepository<Member, String>{

	Boolean existsByUsernameEquals(String username);

	Boolean existsbyNationalId(String nationalld);

}
