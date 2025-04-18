package com.smartbooking.smart_booking.controller;


import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.smartbooking.smart_booking.dto.*;
import com.smartbooking.smart_booking.entity.User;
import com.smartbooking.smart_booking.repository.UserRepository;
import com.smartbooking.smart_booking.security.JwtTokenProvider;
import com.smartbooking.smart_booking.service.inter.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        User user = userService.register(req);
        String token = tokenProvider.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = userRepository.findByUsername(req.getUsername())
            .orElseThrow(); // لن يحدث لأن auth مرت بنجاح
        String token = tokenProvider.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}