package com.bishal.gms.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bishal.gms.entity.Role;
import com.bishal.gms.entity.User;
import com.bishal.gms.repo.UserRepo;

@Component
public class AdminUserIntializer {
	@Bean
    public CommandLineRunner createAdminUser(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234")); // Securely store password
                admin.setRole(Role.ADMIN);

                userRepo.save(admin);
                System.out.println("Default admin created!");
            }
            if (userRepo.findByUsername("user").isEmpty()) {
                User normaluser = new User();
                normaluser.setUsername("user");
                normaluser.setPassword(passwordEncoder.encode("user1234")); // Securely store password
                normaluser.setRole(Role.USER);

                userRepo.save(normaluser);
                System.out.println("Default user created!");
            }
        };
    }
}
