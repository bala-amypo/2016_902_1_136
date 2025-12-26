package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profiles")
public class FinancialProfileController {
    
    @Autowired
    private FinancialProfileService financialProfileService;
    
    @PostMapping
    public ResponseEntity<FinancialProfile> createOrUpdate(@RequestBody FinancialProfile financialProfile) {
        FinancialProfile result = financialProfileService.createOrUpdate(financialProfile);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<FinancialProfile> getByUserId(@PathVariable Long userId) {
        FinancialProfile profile = financialProfileService.getByUserId(userId);
        return ResponseEntity.ok(profile);
    }
}