 package com.bishal.gms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bishal.gms.dto.Role;
import com.bishal.gms.entity.User;
import com.bishal.gms.exception.MandatoryFieldException;
import com.bishal.gms.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<User> registeruser(@RequestBody User user) {
		
		if(user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			throw new MandatoryFieldException("Mandatory field missing");
		}
		user.setRole(Role.CUSTOMER);
		User newUser =  userService.register(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register/admin")
	public ResponseEntity<User> registerByAdmin(@RequestBody User user) {
		
		if(user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			throw new MandatoryFieldException("Mandatory field missing");
		}
		
		User admin =  userService.register(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(admin);
	}
	
	@PostMapping("/login")
	public String loginuser(@RequestBody User user) {
		if(user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			throw new MandatoryFieldException("Mandatory field missing");
		}
		return userService.verify(user);
		
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers(); 
	}
	
}
