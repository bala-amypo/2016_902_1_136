package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RiskAssessmentService;

import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl
        implements RiskAssessmentService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final RiskAssessmentRepository riskRepo;

    public RiskAssessmentServiceImpl(
            LoanRequestRepository loanRepo,
            FinancialProfileRepository profileRepo,
            RiskAssessmentRepository riskRepo) {

        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.riskRepo = riskRepo;
    }

    @Override
    public RiskAssessment assessRisk(Long loanRequestId) {

        if (riskRepo.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Risk already assessed");
        }

        LoanRequest loan = loanRepo.findById(loanRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loan not found"));

        FinancialProfile profile =
                profileRepo.findByUserId(loan.getUser().getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Profile not found"));

        RiskAssessment risk = new RiskAssessment();
        risk.setLoanRequestId(loanRequestId);

        double income = profile.getMonthlyIncome();
        double emi = profile.getExistingLoanEmi();

        double dti = income == 0 ? 0 : (emi / income) * 100;

        risk.setDtiRatio(dti);
        risk.setRiskScore(Math.min(100, dti));
        risk.setCreditCheckStatus(
                profile.getCreditScore() >= 650 ? "PASS" : "FAIL");

        return riskRepo.save(risk);
    }

    @Override
    public RiskAssessment getByLoanRequestId(Long loanRequestId) {
        return riskRepo.findByLoanRequestId(loanRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Risk not found"));
    }
}
