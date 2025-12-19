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
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getLoanRequest(){
        return loanRequest;
    }
    public void setLoanRequest(String loanRequest){
        this.loanRequest=loanRequest;
    }
    public Boolean getIsEligible(){
        return isEligible;
    }
    public void setIsEligible(Boolean isEligible){
        this.isEligible=isEligible;
    }
    public Double getMaxEligibleAmount(){
        return maxEligibleAmount;
    }
    public void setMaxEligibleAmount(Double maxEligibleAmount){
        this.maxEligibleAmount=maxEligibleAmount;
    }
    public Double getEstimatedEmi(){
        return estimatedEmi;
    }
    public void setEstimatedEmi(Double estimatedEmi){
        this.estimatedEmi=estimatedEmi;
    }
    public String getRiskLevel(){
        return riskLevel;
    }
    public void setRiskLevel(string riskLevel){
        this.riskLevel=risklevel;
    }
    public String getRejectionReason(){
        return rejectionReason;
    }
    public void setRejectionReason(String rejectionReason){
        this.rejectionReason=rejectionReason;
    }
    public Timestamp getCalculatedAt(){
        return CalculatedAt;
    }
    public void setCalculateAt(Timestamp calculatedAt){
        this.calculatedAt=calculatedAt;
    }
    public EligibiltyResult(Long id,String loanRequest,Boolean isEligible,Double maxEligibleAmount,Double estimatedEmi,String riskLevel,String rejectionReason,Timestamp calculatedAt){
        this.id=id;
        this.loanRequest=loanRequest;
        this.isEligible=isEligible;
        this.maxEligibleAmount=maxEligibleAmount;
        this.estimatedEmi=estimatedEmi;
        this.riskLevel=riskLevel;
        this.rejectionReason=rejectionReason;
        this.calculatedAt=calculatedAt;
    }
    public Eligibility(){
        
    }

}