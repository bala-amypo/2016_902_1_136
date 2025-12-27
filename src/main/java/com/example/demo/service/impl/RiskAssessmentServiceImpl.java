
// package com.example.demo.service.impl;

// import com.example.demo.entity.FinancialProfile;
// import com.example.demo.entity.LoanRequest;
// import com.example.demo.entity.RiskAssessment;
// import com.example.demo.exception.BadRequestException;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.FinancialProfileRepository;
// import com.example.demo.repository.LoanRequestRepository;
// import com.example.demo.repository.RiskAssessmentRepository;
// import com.example.demo.service.RiskAssessmentService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
//  public class RiskAssessmentServiceImpl implements RiskAssessmentService {
    
//     private final LoanRequestRepository loanRequestRepository;
//     private final FinancialProfileRepository financialProfileRepository;
//     private final RiskAssessmentRepository riskAssessmentRepository;
    
//     public RiskAssessmentServiceImpl(LoanRequestRepository loanRequestRepository,
//                                    FinancialProfileRepository financialProfileRepository,
//                                    RiskAssessmentRepository riskAssessmentRepository) {
//         this.loanRequestRepository = loanRequestRepository;
//         this.financialProfileRepository = financialProfileRepository;
//         this.riskAssessmentRepository = riskAssessmentRepository;
//     }
    
//     @Override
//     public RiskAssessment assessRisk(Long loanRequestId) {
//         // Check if already assessed
//         List<RiskAssessment> existing = riskAssessmentRepository.findByLoanRequestId(loanRequestId);
//         if (!existing.isEmpty()) {
//             throw new BadRequestException("Risk already assessed for this loan request");
//         }
        
//         LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));
        
//         FinancialProfile profile = financialProfileRepository.findByUserId(loanRequest.getUser().getId())
//                 .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
        
//         // Calculate DTI ratio
//         double totalObligations = profile.getMonthlyExpenses() + (profile.getExistingLoanEmi() != null ? profile.getExistingLoanEmi() : 0.0);
//         double dtiRatio = profile.getMonthlyIncome() > 0 ? totalObligations / profile.getMonthlyIncome() : 0.0;
        
//         // Determine credit check status
//         String creditCheckStatus;
//         if (profile.getCreditScore() >= 750) {
//             creditCheckStatus = "APPROVED";
//         } else if (profile.getCreditScore() >= 600) {
//             creditCheckStatus = "PENDING_REVIEW";
//         } else {
//             creditCheckStatus = "REJECTED";
//         }
        
//         // Calculate risk score (0-100)
//         double riskScore = 0.0;
//         if (dtiRatio > 0.5) riskScore += 40;
//         else if (dtiRatio > 0.3) riskScore += 20;
        
//         if (profile.getCreditScore() < 600) riskScore += 40;
//         else if (profile.getCreditScore() < 700) riskScore += 20;
        
//         if (profile.getSavingsBalance() < loanRequest.getRequestedAmount() * 0.1) riskScore += 20;
        
//         RiskAssessment assessment = new RiskAssessment();
//         assessment.setLoanRequestId(loanRequestId);
//         assessment.setDtiRatio(dtiRatio);
//         assessment.setCreditCheckStatus(creditCheckStatus);
//         assessment.setRiskScore(riskScore);
        
//         return riskAssessmentRepository.save(assessment);
//     }
    
//     @Override
//     public RiskAssessment getByLoanRequestId(Long loanRequestId) {
//         List<RiskAssessment> assessments = riskAssessmentRepository.findByLoanRequestId(loanRequestId);
//         if (assessments.isEmpty()) {
//             throw new ResourceNotFoundException("Risk assessment not found");
//         }
//         return assessments.get(0);
//     }
//  }
 public RiskAssessment assessRisk(Long loanRequestId) {

    LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
            .orElseThrow(() -> new BadRequestException("Loan request not found"));

    if (riskAssessmentRepository.findByLoanRequestId(loanRequestId).isPresent()) {
        throw new BadRequestException("Risk already assessed");
    }

    FinancialProfile profile = financialProfileRepository
            .findByUserId(loanRequest.getUser().getId())
            .orElseThrow(() -> new BadRequestException("Financial profile not found"));

    RiskAssessment risk = new RiskAssessment();
    risk.setLoanRequest(loanRequest);

    double income = profile.getMonthlyIncome();
    double expenses = profile.getMonthlyExpenses() + profile.getExistingLoanEmi();

    double dti = income == 0 ? 0.0 : expenses / income;
    risk.setDtiRatio(dti);

    double score = Math.max(0, Math.min(100,
            100 - (dti * 100) + (profile.getCreditScore() - 600) / 3.0));

    risk.setRiskScore(score);

    return riskAssessmentRepository.save(risk);
}
