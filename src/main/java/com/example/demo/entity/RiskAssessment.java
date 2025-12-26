// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// public class RiskAssessmentLog {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String riskLevel;
//     private LocalDateTime evaluatedAt;

//     public RiskAssessmentLog() {
//         this.evaluatedAt = LocalDateTime.now();
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getRiskLevel() { return riskLevel; }
//     public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

//     public LocalDateTime getEvaluatedAt() { return evaluatedAt; }
//     public void setEvaluatedAt(LocalDateTime evaluatedAt) {
//         this.evaluatedAt = evaluatedAt;
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanRequestId;
    private Double dtiRatio;
    private String creditCheckStatus;

    private LocalDateTime timestamp;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getLoanRequestId() {
        return loanRequestId;
    }

    public Double getDtiRatio() {
        return dtiRatio;
    }

    public String getCreditCheckStatus() {
        return creditCheckStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setLoanRequestId(Long loanRequestId) {
        this.loanRequestId = loanRequestId;
    }

    public void setDtiRatio(Double dtiRatio) {
        this.dtiRatio = dtiRatio;
    }

    public void setCreditCheckStatus(String creditCheckStatus) {
        this.creditCheckStatus = creditCheckStatus;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
  

}
