package com.bishal.gms.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bishal.gms.entity.User;
import com.bishal.gms.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepo userRepo;
	
	public CustomUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepo.findByUsername(username)
	    		.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

	        return new CustomUserDetails(user);
	}

}
