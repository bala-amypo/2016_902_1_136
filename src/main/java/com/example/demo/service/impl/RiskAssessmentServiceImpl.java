
// package com.example.demo.service.impl;

// import com.example.demo.entity.FinancialProfile;
// import com.example.demo.entity.LoanRequest;
// import com.example.demo.entity.RiskAssessment;
// import com.example.demo.exception.BadRequestException;
// import com.example.demo.repository.FinancialProfileRepository;
// import com.example.demo.repository.LoanRequestRepository;
// import com.example.demo.repository.RiskAssessmentRepository;
// import com.example.demo.service.RiskAssessmentService;

// public class RiskAssessmentServiceImpl implements RiskAssessmentService {

//     private final LoanRequestRepository loanRequestRepository;
//     private final FinancialProfileRepository financialProfileRepository;
//     private final RiskAssessmentRepository riskAssessmentRepository;

//     public RiskAssessmentServiceImpl(
//             LoanRequestRepository loanRequestRepository,
//             FinancialProfileRepository financialProfileRepository,
//             RiskAssessmentRepository riskAssessmentRepository) {
//         this.loanRequestRepository = loanRequestRepository;
//         this.financialProfileRepository = financialProfileRepository;
//         this.riskAssessmentRepository = riskAssessmentRepository;
//     }

//     @Override
//     public RiskAssessment assessRisk(Long loanRequestId) {

//         LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
//                 .orElseThrow(() -> new BadRequestException("Loan request not found"));

//         if (riskAssessmentRepository.findByLoanRequestId(loanRequestId).isPresent()) {
//             throw new BadRequestException("Risk already assessed");
//         }

//         FinancialProfile profile = financialProfileRepository
//                 .findByUserId(loanRequest.getUser().getId())
//                 .orElseThrow(() -> new BadRequestException("Financial profile not found"));

//         double income = profile.getMonthlyIncome();
//         double obligations =
//                 profile.getMonthlyExpenses() + profile.getExistingLoanEmi();

//         double dtiRatio;
//         if (income == 0) {
//             dtiRatio = 0.0;
//         } else {
//             dtiRatio = obligations / income;
//         }

//         double riskScore =
//                 100 - (dtiRatio * 100)
//                         + (profile.getCreditScore() - 600) / 3.0;

//         if (riskScore < 0) {
//             riskScore = 0;
//         }
//         if (riskScore > 100) {
//             riskScore = 100;
//         }

//         RiskAssessment assessment = new RiskAssessment();
//         assessment.setDtiRatio(dtiRatio);
//         assessment.setRiskScore(riskScore);

//         return riskAssessmentRepository.save(assessment);
//     }

//     @Override
//     public RiskAssessment getByLoanRequestId(Long loanRequestId) {
//         return riskAssessmentRepository.findByLoanRequestId(loanRequestId)
//                 .orElseThrow(() -> new BadRequestException("Risk not found"));
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.RiskAssessmentService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service   // ✅ THIS IS THE KEY FIX
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskAssessmentServiceImpl(
            LoanRequestRepository loanRequestRepository,
            FinancialProfileRepository financialProfileRepository,
            RiskAssessmentRepository riskAssessmentRepository
    ) {
        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    @Override
    public RiskAssessment assessRisk(Long loanRequestId) {

        if (riskAssessmentRepository.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Risk already assessed");
        }

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));

        FinancialProfile profile = financialProfileRepository
                .findByUserId(loanRequest.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));

        RiskAssessment risk = new RiskAssessment();
        risk.setLoanRequest(loanRequest);

        double income = profile.getMonthlyIncome() != null ? profile.getMonthlyIncome() : 0.0;
        double expenses = profile.getMonthlyExpenses() != null ? profile.getMonthlyExpenses() : 0.0;
        double emi = profile.getExistingLoanEmi() != null ? profile.getExistingLoanEmi() : 0.0;

        double dti = income == 0 ? 0 : (expenses + emi) / income;
        risk.setDtiRatio(dti);

        double score = 100 - (dti * 100);
        score = Math.max(0, Math.min(score, 100));

        risk.setRiskScore(score);

        return riskAssessmentRepository.save(risk);
    }

    @Override
    public RiskAssessment getByLoanRequestId(Long loanRequestId) {
        return riskAssessmentRepository.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new BadRequestException("Risk not found"));
    }
}
