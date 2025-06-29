package com.bishal.gms.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bishal.gms.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	private final JWTService jwtService;
	private final UserDetailsService userDetailsService;

	public JWTAuthenticationFilter(JWTService jwtService, UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if(authHeader==null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String jwtToken = authHeader.substring(7); 
		final String username = jwtService.extractUsername(jwtToken);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(username!=null && authentication==null) {
		//authenticate	
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtService.isTokenValid(jwtToken,userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				//for maintaining session id
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
																				.buildDetails(request)
						
						);
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
