package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        // Simple mock logic for eligibility
        EligibilityResult result = new EligibilityResult();
        result.setLoanRequestId(loanRequestId);

        // Example: Eligibility criteria based on requestId (replace with real logic)
        if (loanRequestId % 2 == 0) {
            result.setEligible(true);
            result.setRemarks("Eligible for loan.");
        } else {
            result.setEligible(false);
            result.setRemarks("Not eligible for loan.");
        }

        return result;
    }

    @Override
    public EligibilityResult getResultByRequest(Long loanRequestId) {
        // In a real scenario, fetch from database
        // Here we reuse evaluateEligibility as a stub
        return evaluateEligibility(loanRequestId);
    }
}
