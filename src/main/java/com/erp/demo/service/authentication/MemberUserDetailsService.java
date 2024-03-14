package com.erp.demo.service.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.demo.model.authentication.MemberUserDetails;
import com.erp.demo.model.physical.Member;
import com.erp.demo.repo.MemberRepo;

@Service
public class MemberUserDetailsService implements UserDetailsService {

	@Autowired
	MemberRepo memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepo.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
		return new MemberUserDetails(member);
	}

}
