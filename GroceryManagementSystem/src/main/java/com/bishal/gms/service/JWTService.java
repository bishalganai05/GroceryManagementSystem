package com.bishal.gms.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bishal.gms.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	public String getSecret() {
		return "xg1oOpQ8lZIRPyFS3TObKJp6KnAXW7tA3NfLx63Mbko";
	}
	
	private SecretKey getSecretKey() {
		String secret = getSecret(); 
	    return Keys.hmacShaKeyFor(secret.getBytes());	
	}
	
	public String generateToken(User user) {
		return Jwts.builder()
				.subject(user.getUsername())
				.issuer("Bishal")
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 30 * 1000))
				.signWith(getSecretKey())
				.compact();
	}
	
	private Claims body(String jwtToken) {
		return Jwts
					.parser()
					.verifyWith(getSecretKey())
					.build()
					.parseSignedClaims(jwtToken)
					.getPayload();
	}

	public String extractUsername(String jwtToken) {
		return body(jwtToken).getSubject();
	}
	
	private boolean isTokenExpired(String jwtToken) {
		return body(jwtToken).getExpiration().before(new Date());
	}
	
	public boolean isTokenValid(String username, UserDetails userDetails, String jwtToken) {
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
	}

	

}
