package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;

public interface LoanEligibilityService {

    EligibilityResult checkEligibility(double income, int creditScore);
}
