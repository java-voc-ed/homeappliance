package com.erp.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.Member;

public interface MemberDAO extends JpaRepository<Member, Integer>{

}
