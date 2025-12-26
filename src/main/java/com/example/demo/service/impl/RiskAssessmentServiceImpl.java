package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {
    
    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;
    
    @Autowired
    public RiskAssessmentServiceImpl(LoanRequestRepository loanRequestRepository,
                                     FinancialProfileRepository financialProfileRepository,
                                     RiskAssessmentRepository riskAssessmentRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }
    
    @Override
    @Transactional
    public RiskAssessment assessRisk(Long loanRequestId) {
        // Check if already assessed
        riskAssessmentRepository.findByLoanRequestId(loanRequestId)
            .ifPresent(existing -> {
                throw new BadRequestException("Risk already assessed for this loan request");
            });
        
        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));
        
        FinancialProfile profile = financialProfileRepository.findByUserId(loanRequest.getUser().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
        
        // Calculate risk factors
        double dtiRatio = (profile.getMonthlyExpenses() + profile.getExistingLoanEmi()) / 
                         profile.getMonthlyIncome();
        dtiRatio = Double.isNaN(dtiRatio) ? 0 : dtiRatio;
        
        double savingsRatio = profile.getSavingsBalance() / loanRequest.getRequestedAmount();
        savingsRatio = Double.isNaN(savingsRatio) ? 0 : savingsRatio;
        
        double creditScoreFactor = (900 - profile.getCreditScore()) / 600.0; // Normalized 0-1
        
        // Calculate risk score (0-100, higher is riskier)
        double riskScore = (dtiRatio * 40) + (creditScoreFactor * 40) + 
                          ((1 - Math.min(savingsRatio, 1)) * 20);
        riskScore = Math.min(100, Math.max(0, riskScore));
        
        // Determine risk level
        String riskLevel;
        String recommendation;
        
        if (riskScore <= 30) {
            riskLevel = "LOW";
            recommendation = "Approve with standard terms";
        } else if (riskScore <= 60) {
            riskLevel = "MEDIUM";
            recommendation = "Approve with higher interest rate or additional collateral";
        } else if (riskScore <= 80) {
            riskLevel = "HIGH";
            recommendation = "Consider with strict terms or partial approval";
        } else {
            riskLevel = "VERY HIGH";
            recommendation = "Reject or require substantial collateral";
        }
        
        RiskAssessment assessment = new RiskAssessment();
        assessment.setLoanRequest(loanRequest);
        assessment.setRiskScore(riskScore);
        assessment.setRiskLevel(riskLevel);
        assessment.setDtiRatio(dtiRatio);
        assessment.setRecommendation(recommendation);
        assessment.setNotes(String.format("Credit Score: %d, DTI: %.2f, Savings Ratio: %.2f", 
            profile.getCreditScore(), dtiRatio, savingsRatio));
        assessment.setAssessedAt(LocalDateTime.now());
        
        return riskAssessmentRepository.save(assessment);
    }
    
    @Override
    public RiskAssessment getByLoanRequestId(Long loanRequestId) {
        return riskAssessmentRepository.findByLoanRequestId(loanRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("Risk assessment not found"));
    }
}