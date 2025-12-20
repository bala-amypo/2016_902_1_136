package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final EligibilityResultRepository repository;

    public EligibilityServiceImpl(EligibilityResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public EligibilityResult checkEligibility(Long loanRequestId) {
        return repository.findByLoanRequestId(loanRequestId);
    }
}
