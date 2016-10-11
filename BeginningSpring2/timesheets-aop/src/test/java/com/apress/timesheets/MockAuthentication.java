package com.apress.timesheets;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;

public class MockAuthentication implements Authentication {
	private static final long serialVersionUID = 0L;
	
	private boolean isAuthenticated = false;
	private Object principal = null;
	private Object credentials;
	private Object details;
	private String name;
	private GrantedAuthority[] authorities;
	
	public MockAuthentication() {
	}
	
	public MockAuthentication(final Object principal) {
		this.principal = principal;
	}

	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}
	
	public void setGrantedAuthority(final GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}

	public Object getCredentials() {
		return credentials;
	}
	
	public void setCredentials(final Object credentials) {
		this.credentials = credentials;
	}

	public Object getDetails() {
		return this.details;
	}
	
	public void setDetails(final Object details) {
		this.details = details;
	}

	public Object getPrincipal() {
		return principal;
	}
	
	public void setPrincipal(final Object principal) {
		this.principal = principal;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
}
