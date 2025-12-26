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

    private String userId;          // ID of the user this risk assessment belongs to
    private double riskScore;       // Risk score
    private String riskLevel;       // e.g., LOW, MEDIUM, HIGH
    private String comments;        // Optional comments or notes

    // Default constructor
    public RiskAssessment() {
    }

    // Parameterized constructor
    public RiskAssessment(String userId, double riskScore, String riskLevel, String comments) {
        this.userId = userId;
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
        this.comments = comments;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getRiskScore() {
        return riskScore;
    }

    // This is the method your service needed
    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
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
                ", userId='" + userId + '\'' +
                ", riskScore=" + riskScore +
                ", riskLevel='" + riskLevel + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
