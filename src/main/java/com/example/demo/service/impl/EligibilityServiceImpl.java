package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityService;

import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository eligibilityRepo;

    public EligibilityServiceImpl(
            LoanRequestRepository loanRepo,
            FinancialProfileRepository profileRepo,
            EligibilityResultRepository eligibilityRepo) {

        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.eligibilityRepo = eligibilityRepo;
    }

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {

        if (eligibilityRepo.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Eligibility already evaluated");
        }

        LoanRequest loan = loanRepo.findById(loanRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loan not found"));

        FinancialProfile profile =
                profileRepo.findByUserId(loan.getUser().getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Profile not found"));

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loan);

        double disposableIncome =
                profile.getMonthlyIncome() -
                profile.getMonthlyExpenses() -
                profile.getExistingLoanEmi();

        double maxEligibleAmount = disposableIncome * 12;

        result.setMaxEligibleAmount(Math.max(0, maxEligibleAmount));
        result.setIsEligible(maxEligibleAmount > 0);
        result.setRiskLevel(
                profile.getCreditScore() >= 700 ? "LOW" : "HIGH");

        return eligibilityRepo.save(result);
    }

    @Override
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return eligibilityRepo.findByLoanRequestId(loanRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Eligibility not found"));
    }
}
