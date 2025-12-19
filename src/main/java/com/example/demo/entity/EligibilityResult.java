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
    public void setriskLevel(string riskLevel){
        this.riskLevel=risklevel;
    }
    public String getrejectionReason(){
        return rejectionReason;
    }
    public void setrejectionReason(String rejectionReason){
        this.rejectionReason=rejectionReason;
    }
    public Timestamp getcalculatedAt(){
        return calculatedAt;
    }
    public void setcalculateAt(Timestamp calculatedAt){
        this.calculatedAt=calculatedAt;
    }

}