package com.bishal.gms.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bishal.gms.entity.User;
import com.bishal.gms.repo.UserRepo;

@Service
public class UserService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserRepo userRepo;

	private final AuthenticationManager authenticationManager;

	private final JWTService jwtService;

	public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
			AuthenticationManager authenticationManager, JWTService jwtService) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	public User register(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public String verify(User user) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

			if (authentication.isAuthenticated()) {
				return jwtService.generateToken(user);
			} else {
				return "Authentication Failed";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Authentication Exception: " + e.getMessage();
		}
	}

}
