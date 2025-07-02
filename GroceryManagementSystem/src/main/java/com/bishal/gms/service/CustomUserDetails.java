package com.bishal.gms.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bishal.gms.entity.User;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails{

	private final User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()));
		authorities.addAll(
				user.getRole().getPermissions()
								.stream()
								.map(permissions-> new SimpleGrantedAuthority(permissions.name()))
								.collect(Collectors.toSet())
		
				);
		
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
