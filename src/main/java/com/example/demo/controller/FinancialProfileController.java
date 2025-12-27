package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profiles")
@Tag(name = "Financial Profile", description = "Financial profile management")
public class FinancialProfileController {
    
    private final FinancialProfileService financialProfileService;
    
    public FinancialProfileController(FinancialProfileService financialProfileService) {
        this.financialProfileService = financialProfileService;
    }
    
    @PostMapping
    public ResponseEntity<FinancialProfile> createOrUpdate(@RequestBody FinancialProfile profile) {
        FinancialProfile saved = financialProfileService.createOrUpdate(profile);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<FinancialProfile> getByUserId(@PathVariable Long userId) {
        FinancialProfile profile = financialProfileService.getByUserId(userId);
        return ResponseEntity.ok(profile);
    }
}