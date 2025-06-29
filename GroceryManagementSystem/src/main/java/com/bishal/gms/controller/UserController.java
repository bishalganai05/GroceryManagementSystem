package com.bishal.gms.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bishal.gms.entity.User;
import com.bishal.gms.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public User registeruser(@RequestBody User user) {
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String loginuser(@RequestBody User user) {
		return userService.verify(user);
		
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	
	
}
