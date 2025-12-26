package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String email;
    private String role;
    private Long userId;
    
    public AuthResponse() {}
    
    public AuthResponse(String token, String email, String role, Long userId) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.userId = userId;
    }
}