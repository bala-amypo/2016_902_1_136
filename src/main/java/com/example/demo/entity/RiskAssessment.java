package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "risk_assessment")
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanRequestId;     // Loan request ID
    private String userId;          // User ID
    private double dtiRatio;        // Debt-to-Income ratio
    private double riskScore;       // Risk score
    private String riskLevel;       // LOW, MEDIUM, HIGH
    private String creditCheckStatus; // e.g., PASS / FAIL
    private String comments;        // Optional notes

    // Default constructor
    public RiskAssessment() {}

    // Parameterized constructor
    public RiskAssessment(Long loanRequestId, String userId, double dtiRatio,
                          double riskScore, String riskLevel, String creditCheckStatus, String comments) {
        this.loanRequestId = loanRequestId;
        this.userId = userId;
        this.dtiRatio = dtiRatio;
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
        this.creditCheckStatus = creditCheckStatus;
        this.comments = comments;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanRequestId() {
        return loanRequestId;
    }

    public void setLoanRequestId(Long loanRequestId) {
        this.loanRequestId = loanRequestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getDtiRatio() {
        return dtiRatio;
    }

    public void setDtiRatio(double dtiRatio) {
        this.dtiRatio = dtiRatio;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getCreditCheckStatus() {
        return creditCheckStatus;
    }

    public void setCreditCheckStatus(String creditCheckStatus) {
        this.creditCheckStatus = creditCheckStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "RiskAssessment{" +
                "id=" + id +
                ", loanRequestId=" + loanRequestId +
                ", userId='" + userId + '\'' +
                ", dtiRatio=" + dtiRatio +
                ", riskScore=" + riskScore +
                ", riskLevel='" + riskLevel + '\'' +
                ", creditCheckStatus='" + creditCheckStatus + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
