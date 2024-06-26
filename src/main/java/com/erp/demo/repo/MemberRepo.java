package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.Member;

public interface MemberRepo extends JpaRepository<Member, Integer>{

	Boolean existsByUsernameEquals(String username);

	Boolean existsByNationalIdEquals(String nationalld);

	Member findByUsername(String username);

}
