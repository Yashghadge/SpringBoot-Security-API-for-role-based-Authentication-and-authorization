package com.role.security.models;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeLogin implements UserDetails {
	
	public EmployeeLogin(String username, @Size(min = 8) String password,
			Collection<? extends GrantedAuthority> authorities) {
		
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	@Column(unique = true,nullable = false)
	private String username;
	@Size(min = 7)
	private String password;
	
     Collection<? extends GrantedAuthority> authorities;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
