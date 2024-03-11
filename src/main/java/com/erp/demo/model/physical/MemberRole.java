package com.erp.demo.model.physical;

import org.springframework.security.core.GrantedAuthority;

/**
 * TODO: MAKE IT AN ENTITY.
 */
public class MemberRole implements GrantedAuthority {

	@Override
	public String getAuthority() {
		return "ROLE_MEMBER";
	}

}
