package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {
    
    @Autowired
    private EligibilityService eligibilityService;
    
    @PostMapping("/evaluate/{loanRequestId}")
    public ResponseEntity<EligibilityResult> evaluate(@PathVariable Long loanRequestId) {
        EligibilityResult result = eligibilityService.evaluateEligibility(loanRequestId);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/loan-request/{loanRequestId}")
    public ResponseEntity<EligibilityResult> getByLoanRequest(@PathVariable Long loanRequestId) {
        EligibilityResult result = eligibilityService.getByLoanRequestId(loanRequestId);
        return ResponseEntity.ok(result);
    }
}