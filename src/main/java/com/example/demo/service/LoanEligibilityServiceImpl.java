package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.repository.EligibilityResultRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    private final EligibilityResultRepository repository;

    public LoanEligibilityServiceImpl(EligibilityResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public EligibilityResult checkEligibility(double income, int creditScore) {

        EligibilityResult result = new EligibilityResult();

        if (income >= 30000 && creditScore >= 700) {
            result.setEligible(true);
            result.setMessage("Eligible for loan");
        } else {
            result.setEligible(false);
            result.setMessage("Not eligible for loan");
        }

        return repository.save(result);
    }
}
