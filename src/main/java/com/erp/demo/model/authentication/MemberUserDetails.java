package com.erp.demo.model.authentication;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.erp.demo.model.physical.Member;
import com.erp.demo.model.physical.MemberRole;

public class MemberUserDetails implements UserDetails {

	private Member member;
	
	public MemberUserDetails(Member member) {
		this.member = member;
	}
	
	public Integer getMid() {
		return member.getMid();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new MemberRole());
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
