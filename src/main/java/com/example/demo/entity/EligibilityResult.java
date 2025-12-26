// package com.example.demo.entity;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "eligibility_result")
// public class EligibilityResult {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private boolean eligible;

//     private String message;

    
//     public EligibilityResult() {}


//     public Long getId() {
//         return id;
//      }

//     public boolean isEligible() {
//         return eligible;
//     }

//     public void setEligible(boolean eligible) {
//         this.eligible = eligible;
//     }

//     public String getMessage() {
//         return message;
//     }

//     public void setMessage(String message) {
//         this.message = message;
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest;

    private Boolean isEligible;
    private Double maxEligibleAmount;
    private Double estimatedEmi;

    private String riskLevel; // LOW / MEDIUM / HIGH
    private String rejectionReason;

    private LocalDateTime calculatedAt;

    // Getters
    public Boolean getIsEligible() {
        return isEligible;
    }

    public Double getMaxEligibleAmount() {
        return maxEligibleAmount;
    }

    public Double getEstimatedEmi() {
        return estimatedEmi;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    // Setters
    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public void setIsEligible(Boolean eligible) {
        isEligible = eligible;
    }

    public void setMaxEligibleAmount(Double maxEligibleAmount) {
        this.maxEligibleAmount = maxEligibleAmount;
    }

    public void setEstimatedEmi(Double estimatedEmi) {
        this.estimatedEmi = estimatedEmi;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}

