package com.bishal.gms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bishal.gms.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	//private String secretKey = null;

	public String generateToken(User user) {
		Map<String, Object> claimsMap = new HashMap<>();
		return Jwts.builder()
				.claims()
				.add(claimsMap)
				.subject(user.getUsername())
				.issuer("Bishal")
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 30 * 1000))
				.and()
				.signWith(generatedKey())
				.compact();
	}

	private SecretKey generatedKey() {
		byte[] decode = Decoders.BASE64URL.decode(getSecretKey()); 
	    return Keys.hmacShaKeyFor(decode);	
	}

	public String getSecretKey() {
		return "xg1oOpQ8lZIRPyFS3TObKJp6KnAXW7tA3NfLx63Mbko";
	}

	public String extractUsername(String jwtToken) {
		return extractClaims(jwtToken,Claims::getSubject);
	}

	private <T> T extractClaims(String jwtToken, Function<Claims,T> claimResolver) {
		Claims claims = extractClaims(jwtToken);
		return claimResolver.apply(claims);
	}

	private Claims extractClaims(String jwtToken) {
		
		return Jwts
				.parser()
				.verifyWith(generatedKey())
				.build()
				.parseSignedClaims(jwtToken)
				.getPayload();
	}

	public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
		final String usernameString = extractUsername(jwtToken);
		return (usernameString.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
	}

	private boolean isTokenExpired(String jwtToken) {
		return extractExpiration(jwtToken).before(new Date());
	}

	private Date extractExpiration(String jwtToken) {
		return extractClaims(jwtToken,Claims::getExpiration);
	}
}
