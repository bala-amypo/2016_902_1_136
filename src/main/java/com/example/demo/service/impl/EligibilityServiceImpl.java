package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EligibilityServiceImpl implements EligibilityService {
    
    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final EligibilityResultRepository eligibilityResultRepository;
    
    @Autowired
    public EligibilityServiceImpl(LoanRequestRepository loanRequestRepository,
                                 FinancialProfileRepository financialProfileRepository,
                                 EligibilityResultRepository eligibilityResultRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
    }
    
    @Override
    @Transactional
    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        // Check if already evaluated
        eligibilityResultRepository.findByLoanRequestId(loanRequestId)
            .ifPresent(existing -> {
                throw new BadRequestException("Eligibility already evaluated for this loan request");
            });
        
        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));
        
        FinancialProfile profile = financialProfileRepository.findByUserId(loanRequest.getUser().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
        
        // Calculate eligibility
        double monthlyDisposableIncome = profile.getMonthlyIncome() - 
                                        profile.getMonthlyExpenses() - 
                                        profile.getExistingLoanEmi();
        
        // Basic eligibility criteria
        boolean isEligible = monthlyDisposableIncome > 0 && 
                           profile.getCreditScore() >= 650 &&
                           profile.getSavingsBalance() >= 1000;
        
        // Calculate max eligible amount (simplified formula)
        double maxEligibleAmount = 0.0;
        double recommendedEmi = 0.0;
        
        if (isEligible) {
            maxEligibleAmount = monthlyDisposableIncome * loanRequest.getTenureMonths() * 0.4; // 40% of disposable income
            maxEligibleAmount = Math.min(maxEligibleAmount, profile.getSavingsBalance() * 10); // Max 10x savings
            
            double rate = 0.10; // 10% annual interest
            double monthlyRate = rate / 12;
            int tenure = Math.min(loanRequest.getTenureMonths(), 60); // Max 5 years
            
            recommendedEmi = maxEligibleAmount * monthlyRate * Math.pow(1 + monthlyRate, tenure) /
                           (Math.pow(1 + monthlyRate, tenure) - 1);
        }
        
        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setIsEligible(isEligible);
        result.setMaxEligibleAmount(maxEligibleAmount);
        result.setRecommendedTenureMonths(loanRequest.getTenureMonths());
        result.setMonthlyEmi(recommendedEmi);
        result.setReason(isEligible ? "Eligible based on income, credit score, and savings" : 
                         "Not eligible due to insufficient income, low credit score, or inadequate savings");
        result.setCalculatedAt(LocalDateTime.now());
        
        return eligibilityResultRepository.save(result);
    }
    
    @Override
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return eligibilityResultRepository.findByLoanRequestId(loanRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("Eligibility result not found"));
    }
}