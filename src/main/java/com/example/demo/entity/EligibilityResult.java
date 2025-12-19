package com.example.demo.entity;
import java.Time.*;
import jakarta.persisitence.*;
public class EligibilityResult{
    @OneToOneLoanRequest
    private Long id;
    private String loanRequest;
    private Boolean isEligible;
    private Double maxEligibleAmount;
    private Double estimatedEmi;
    private String riskLevel;
    private String rejectionReason;
    private 
}