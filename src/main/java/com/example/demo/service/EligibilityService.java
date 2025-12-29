package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;

@Service
public interface EligibilityService {
    EligibilityResult evaluateEligibility(Long loanRequestId);
    EligibilityResult getByLoanRequestId(Long loanRequestId);
}