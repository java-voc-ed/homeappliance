package com.erp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.erp.demo.model.physical.Member;
import com.erp.demo.service.internal.MemberMgmt;

@SpringBootApplication
public class HomeapplianceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HomeapplianceApplication.class, args);
	}

	
	@Autowired
	MemberMgmt memberMgmt;
	
	@Override
	public void run(String... args) throws Exception {
//		List<Member> members = memberMgmt.getAll();
//		members.forEach(member -> memberMgmt.update(member));		
	}

}
