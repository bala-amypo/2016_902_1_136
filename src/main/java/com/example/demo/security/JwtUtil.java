package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    
    private final SecretKey secretKey;
    private final long expirationMs;
    
    public JwtUtil() {
        // In production, use a secure key from configuration
        this.secretKey = Keys.hmacShaKeyFor("ChangeThisSecretForProductionButKeepItLongEnough".getBytes());
        this.expirationMs = 3600000; // 1 hour
    }
    
    public JwtUtil(String secret, long expirationMs) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }
    
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());
        
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }
    
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }
    
    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    
    public String getUsername(String token) {
        return getAllClaims(token).getSubject();
    }
    
    public Date getExpirationDate(String token) {
        return getAllClaims(token).getExpiration();
    }
    
    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }
    
    public boolean validateToken(String token, User user) {
        final String username = getUsername(token);
        return (username.equals(user.getEmail()) && !isTokenExpired(token));
    }
}