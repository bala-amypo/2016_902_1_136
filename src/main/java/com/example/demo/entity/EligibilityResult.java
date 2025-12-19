package com.example.demo.entity;
import java.Time.*;
import jakarta.persisitence.*;

@Entity
public class EligibilityResult{
    @OneToOneLoanRequest
    @Id;
    @GeneratedValue(strategy=GeneratitonType.IDENTITY)
    private Long id;
    private String loanRequest;
    private Boolean isEligible;
    private Double maxEligibleAmount;
    private Double estimatedEmi;
    private String riskLevel;
    private String rejectionReason;
    private Timestamp calculatedAt;

    public Long getId(){

    }
}