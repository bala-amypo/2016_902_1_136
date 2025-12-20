package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    @GetMapping("/{loanId}")
    public String checkEligibility(@PathVariable Long loanId) {
        return "Eligibility check completed for loanId: " + loanId;
    }
}
