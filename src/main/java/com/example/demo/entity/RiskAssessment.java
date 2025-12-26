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

@Entity
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private LoanRequest loanRequest;

    private String riskLevel;
    private Double emiRatio;

    // getters & setters
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }
 
    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public String getRiskLevel() {
        return riskLevel;
    }
 
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Double getEmiRatio() {
        return emiRatio;
     }
 
    public void setEmiRatio(Double emiRatio) {
        this.emiRatio = emiRatio;
    }
}


