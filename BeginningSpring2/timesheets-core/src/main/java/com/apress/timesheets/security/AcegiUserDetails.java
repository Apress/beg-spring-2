package com.apress.timesheets.security;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;

@Entity
@NamedQueries({
   @NamedQuery(name="findAcegiUserByName",query="from AcegiUserDetails where userAccount.accountName = :username")
})
public class AcegiUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 0L;
	private Long id;
	private UserAccount userAccount;
	private String password;
	private boolean accountNonExpired = false;
	private boolean accountNonLocked = false;
	private boolean credentialsNonExpired = false;
	private boolean enabled = false;

	public AcegiUserDetails() {
	}
	
	public AcegiUserDetails(final UserAccount userAccount, final String password) {
		this.userAccount = userAccount;
		this.password = password;
		accountNonExpired = true;
		accountNonLocked = true;
		credentialsNonExpired = true;
		enabled = true;
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@ManyToOne(optional=false) 
    @JoinColumn(unique=true)	
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Transient
	public GrantedAuthority[] getAuthorities() {
		final Set<UserRole> roles = userAccount.getRoles();
		return AcegiGrantedAuthority.getAuthorities(roles);
	}

	@Transient
	public String getUsername() {
		return userAccount.getAccountName();
	}

}
