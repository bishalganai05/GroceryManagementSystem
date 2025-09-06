package com.bishal.gms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
        
        private final UserDetailsService userDetailsService;
        private final JWTAuthenticationFilter jwtAuthenticationFilter;
        
    public SecurityConfig(UserDetailsService userDetailsService, JWTAuthenticationFilter jwtAuthenticationFilter) {
                this.userDetailsService = userDetailsService;
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder(14);
        }
    
        @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                
                httpSecurity
                                        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .csrf(csrf->csrf.disable())
                                        .authorizeHttpRequests(request->request
                                                                                                                        .requestMatchers("/users/register", "/users/login").permitAll()
                                                                                                                        //.requestMatchers(HttpMethod.GET,"/products/**").hasAuthority(Permissions.PRODUCT_READ.name())
                                                                                                                        //.requestMatchers(HttpMethod.POST,"/products/**").hasAuthority(Permissions.PRODUCT_WRITE.name())
                                                                                                                        //.requestMatchers(HttpMethod.DELETE,"/products/**").hasAuthority(Permissions.PRODUCT_DELETE.name())
                                                                                                                        .requestMatchers(
                                                                                                                    "/v3/api-docs/**",
                                                                                                                    "/swagger-ui/**",
                                                                                                                    "/swagger-ui.html"
                                                                                                                ).permitAll()
                                                                                                                        .anyRequest().authenticated())
                                        .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class );
                                        
                
                return httpSecurity.build();
        }

    @Bean
    AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        
        return new ProviderManager(daoAuthenticationProvider);
    }
    

    
        //@Bean
//      public UserDetailsService userDetailsService() {
//              UserDetails bishal = User.withUsername("bishal")
//                                                               .password("{noop}bishal1234") //No Op password encoder
//                                                               .roles("USER")
//                                                               .build();
//              
//              UserDetails ganai = User.withUsername("ganai")
//                                                              .password("{noop}ganai1234")
//                                                              .roles("USER")
//                                                              .build();
//              
//              return new InMemoryUserDetailsManager(bishal,ganai);
//      }
//

//
//    @SuppressWarnings("deprecation")
//    @Bean
//    AuthenticationProvider authenticationProvider() {
//              DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//              daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//              daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//              
//              return daoAuthenticationProvider;
//      }
//    
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//      return authenticationConfiguration.getAuthenticationManager();
//    }
}
