package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        User user = userService.findByEmail(authRequest.getEmail());
        
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new com.example.demo.exception.BadRequestException("Invalid credentials");
        }
        
        String token = jwtUtil.generateToken(user);
        AuthResponse response = new AuthResponse(
            token, 
            user.getEmail(), 
            user.getRole(), 
            user.getId()
        );
        
        return ResponseEntity.ok(response);
    }
}