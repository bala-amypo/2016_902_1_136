package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {
    
    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final EligibilityResultRepository eligibilityResultRepository;
    
    public EligibilityServiceImpl(LoanRequestRepository loanRequestRepository, 
                                 FinancialProfileRepository financialProfileRepository,
                                 EligibilityResultRepository eligibilityResultRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
    }
    
    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        // Check if already evaluated
        if (eligibilityResultRepository.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Eligibility already evaluated");
        }
        
        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));
        
        FinancialProfile profile = financialProfileRepository.findByUserId(loanRequest.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
        
        // Calculate DTI ratio
        double totalObligations = profile.getMonthlyExpenses() + (profile.getExistingLoanEmi() != null ? profile.getExistingLoanEmi() : 0.0);
        double dtiRatio = profile.getMonthlyIncome() > 0 ? totalObligations / profile.getMonthlyIncome() : 0.0;
        
        // Determine eligibility
        boolean isEligible = true;
        String rejectionReason = null;
        String riskLevel = "LOW";
        
        if (profile.getCreditScore() < 600) {
            isEligible = false;
            rejectionReason = "Credit score too low";
            riskLevel = "HIGH";
        } else if (dtiRatio > 0.5) {
            isEligible = false;
            rejectionReason = "Debt-to-income ratio too high";
            riskLevel = "HIGH";
        } else if (dtiRatio > 0.3) {
            riskLevel = "MEDIUM";
        }
        
        // Calculate max eligible amount
        double maxEligibleAmount = 0.0;
        if (isEligible) {
            double availableIncome = profile.getMonthlyIncome() - totalObligations;
            maxEligibleAmount = Math.min(loanRequest.getRequestedAmount(), availableIncome * loanRequest.getTenureMonths() * 0.8);
        }
        
        // Calculate estimated EMI
        double estimatedEmi = maxEligibleAmount > 0 ? maxEligibleAmount / loanRequest.getTenureMonths() : 0.0;
        
        EligibilityResult result = new EligibilityResult(loanRequest, isEligible, maxEligibleAmount, estimatedEmi, riskLevel);
        result.setRejectionReason(rejectionReason);
        
        return eligibilityResultRepository.save(result);
    }
    
    @Override
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return eligibilityResultRepository.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Eligibility result not found"));
    }
}