package com.smartbooking.security;

import com.smartbooking.security.JwtAuthenticationFilter;
import com.smartbooking.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}






/**
 * SecurityConfig Class
 * 
 * This class configures the security settings for the application using Spring Security.
 * It defines the security filter chain, authentication manager, and password encoder.
 * 
 * Dependencies:
 * - JwtAuthenticationFilter: A custom filter for handling JWT-based authentication.
 * - CustomUserDetailsService: A service for loading user-specific data during authentication.
 * 
 * Annotations:
 * - @Bean: Marks methods that produce Spring-managed beans.
 * 
 * Methods:
 * 
 * 1. filterChain(HttpSecurity http):
 *    - Configures the security filter chain for the application.
 *    - Disables CSRF protection as the application uses stateless authentication.
 *    - Configures session management to be stateless (`SessionCreationPolicy.STATELESS`).
 *    - Defines URL patterns that are publicly accessible (e.g., `/api/auth/**`, `/swagger-ui/**`, `/v3/api-docs/**`).
 *    - Requires authentication for all other requests.
 *    - Sets the `CustomUserDetailsService` for user authentication.
 *    - Adds the `JwtAuthenticationFilter` before the `UsernamePasswordAuthenticationFilter` to handle JWT validation.
 *    - Returns the configured `SecurityFilterChain`.
 * 
 * 2. authenticationManager(AuthenticationConfiguration config):
 *    - Provides the `AuthenticationManager` bean.
 *    - Retrieves the `AuthenticationManager` from the `AuthenticationConfiguration`.
 *    - Used for authenticating users during login.
 * 
 * 3. passwordEncoder():
 *    - Provides the `PasswordEncoder` bean.
 *    - Uses `BCryptPasswordEncoder` to hash passwords securely.
 *    - Ensures that passwords are stored in a secure, non-reversible format.
 * 
 * Usage:
 * - This class is automatically detected and used by Spring Security to configure the application's security settings.
 * - The `JwtAuthenticationFilter` ensures that JWT tokens are validated for protected endpoints.
 * - The `PasswordEncoder` is used for encoding and verifying user passwords.
 * 
 * Notes:
 * - Ensure that the `JwtAuthenticationFilter` is properly implemented to handle JWT validation.
 * - Publicly accessible endpoints (e.g., `/api/auth/**`) should be carefully reviewed to avoid exposing sensitive data.
 * - Use strong password policies and hashing algorithms to enhance security.
 */