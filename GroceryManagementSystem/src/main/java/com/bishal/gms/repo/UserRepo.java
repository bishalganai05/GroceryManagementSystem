package com.bishal.gms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bishal.gms.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
	User findByUsername(String username);
}
