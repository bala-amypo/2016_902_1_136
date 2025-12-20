package com.example.demo.controller;

import com.example.demo.entity.LoanEligibility;
import com.example.demo.repository.LoanEligibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    @Autowired
    private LoanEligibilityRepository eligibilityRepository;

    @PostMapping
    public LoanEligibility save(@RequestBody LoanEligibility eligibility) {
        return eligibilityRepository.save(eligibility);
    }

    @GetMapping("/{id}")
    public LoanEligibility get(@PathVariable Long id) {
        return eligibilityRepository.findById(id).orElse(null);
    }
}
