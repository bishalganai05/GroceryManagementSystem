package com.bishal.gms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final JWTAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(UserDetailsService userDetailsService, JWTAuthenticationFilter jwtAuthenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
					.csrf(csrf->csrf.disable())
					.authorizeHttpRequests(request->request
															.requestMatchers("/users/register", "/users/login").permitAll()
															.anyRequest().authenticated())
					.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class )
					.httpBasic(Customizer.withDefaults());
		
		
		return httpSecurity.build();
	}
	
	//@Bean
	public UserDetailsService userDetailsService() {
		UserDetails bishal = User.withUsername("bishal")
								 .password("{noop}bishal1234") //No Op password encoder
								 .roles("USER")
								 .build();
		
		UserDetails ganai = User.withUsername("ganai")
								.password("{noop}ganai1234")
								.roles("USER")
								.build();
		
		return new InMemoryUserDetailsManager(bishal,ganai);
	}

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(14);
	}

    @SuppressWarnings("deprecation")
    @Bean
    AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		
		return daoAuthenticationProvider;
	}
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
}
