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

// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class RiskAssessment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @OneToOne
//     private LoanRequest loanRequest;

//     private String riskLevel;
//     private Double emiRatio;

//     // getters & setters
//     public Long getId() {
//         return id;
//     }
 
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public LoanRequest getLoanRequest() {
//         return loanRequest;
//     }
 
//     public void setLoanRequest(LoanRequest loanRequest) {
//         this.loanRequest = loanRequest;
//     }

//     public String getRiskLevel() {
//         return riskLevel;
//     }
 
//     public void setRiskLevel(String riskLevel) {
//         this.riskLevel = riskLevel;
//     }

//     public Double getEmiRatio() {
//         return emiRatio;
//      }
 
//     public void setEmiRatio(Double emiRatio) {
//         this.emiRatio = emiRatio;
//     }
// }


package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessments")
@Data
public class RiskAssessment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_request_id", nullable = false, unique = true)
    private LoanRequest loanRequest;
    
    @Column(name = "risk_score", nullable = false)
    private Double riskScore;
    
    @Column(name = "risk_level", nullable = false)
    private String riskLevel;
    
    @Column(name = "dti_ratio")
    private Double dtiRatio;
    
    @Column(name = "recommendation")
    private String recommendation;
    
    @Column(name = "notes")
    private String notes;
    
    @CreationTimestamp
    @Column(name = "assessed_at", updatable = false)
    private LocalDateTime assessedAt;
}