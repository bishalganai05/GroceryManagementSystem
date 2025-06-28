package com.bishal.gms.service;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bishal.gms.entity.User;
import com.bishal.gms.repo.UserRepo;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserRepo userRepo;
	
	public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User register(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()) );
		return userRepo.save(user);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
