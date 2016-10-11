package com.apress.timesheets.security;

import java.util.Set;

import org.acegisecurity.GrantedAuthority;

import com.apress.timesheets.entity.UserRole;

public class AcegiGrantedAuthority implements GrantedAuthority {
	
	private static final long serialVersionUID = 0L;
	
	private UserRole role;
	
	public AcegiGrantedAuthority(final UserRole role) {
		this.role = role;
	}
	
	public String getAuthority() {
		return role.getRoleName();
	}
	
	public UserRole getUserRole() {
		return role;
	}
	
	public static GrantedAuthority[] getAuthorities(final Set<UserRole> roles) {
		final GrantedAuthority[] authorities = new GrantedAuthority[roles.size()];
		int index = 0;
		for( final UserRole role : roles ) {
			authorities[index++] = new AcegiGrantedAuthority(role);
		}
		return authorities;
	}
}
