package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        return new EligibilityResult(); // placeholder
    }

    @Override
    public EligibilityResult getResultByRequest(Long loanRequestId) {
        return new EligibilityResult(); // placeholder
    }
}
