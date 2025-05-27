package com.example.harudam.auth.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public class AuthUser {
	private final Long id;
	private final String email;
	private final String name;
	private final Collection<? extends GrantedAuthority> authorities;

	public AuthUser(Long id, String email, String name, UserRole userRole) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.authorities = List.of(new SimpleGrantedAuthority(userRole.name()));
	}

	public UserRole getRole(){
		return authorities.stream().map(GrantedAuthority::getAuthority)
			.map(UserRole::of).findFirst().orElseThrow(InvalidUserRoleException::new);
	}
}
