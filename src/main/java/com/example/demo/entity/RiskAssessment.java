// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "risk_assessments")
// public class RiskAssessment {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(nullable = false)
//     private Long loanRequestId;
    
//     @Column(nullable = false)
//     private Double dtiRatio;
    
//     @Column(nullable = false)
//     private String creditCheckStatus;
    
//     @Column(nullable = false)
//     private LocalDateTime timestamp;
    
//     private Double riskScore;
    
//     @PrePersist
//     protected void onCreate() {
//         timestamp = LocalDateTime.now();
//     }
    
//     public RiskAssessment() {}
    
//     public RiskAssessment(Long loanRequestId, Double dtiRatio, String creditCheckStatus) {
//         this.loanRequestId = loanRequestId;
//         this.dtiRatio = dtiRatio;
//         this.creditCheckStatus = creditCheckStatus;
//     }
    
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public Long getLoanRequestId() { return loanRequestId; }
//     public void setLoanRequestId(Long loanRequestId) { this.loanRequestId = loanRequestId; }
    
//     public Double getDtiRatio() { return dtiRatio; }
//     public void setDtiRatio(Double dtiRatio) { this.dtiRatio = dtiRatio; }
    
//     public String getCreditCheckStatus() { return creditCheckStatus; }
//     public void setCreditCheckStatus(String creditCheckStatus) { this.creditCheckStatus = creditCheckStatus; }
    
//     public LocalDateTime getTimestamp() { return timestamp; }
//     public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
//     public Double getRiskScore() { return riskScore; }
//     public void setRiskScore(Double riskScore) { this.riskScore = riskScore; }
// }

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private LoanRequest loanRequest;

    private Double riskScore;

    private Double dtiRatio;

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Other getters and setters
    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public Double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Double riskScore) {
        this.riskScore = riskScore;
    }

    public Double getDtiRatio() {
        return dtiRatio;
    }

    public void setDtiRatio(Double dtiRatio) {
        this.dtiRatio = dtiRatio;
    }
}
