// package com.example.demo.entity;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "eligibility_result")
// public class EligibilityResult {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private boolean eligible;

//     private String message;

    
//     public EligibilityResult() {}


//     public Long getId() {
//         return id;
//     }

//     public boolean isEligible() {
//         return eligible;
//     }

//     public void setEligible(boolean eligible) {
//         this.eligible = eligible;
//     }

//     public String getMessage() {
//         return message;
//     }

//     public void setMessage(String message) {
//         this.message = message;
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private LoanRequest loanRequest;

    private Boolean eligible;
    private Double maxEligibleAmount;

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

    public Boolean getEligible() {
        return eligible;
    }
 
    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    public Double getMaxEligibleAmount() {
        return maxEligibleAmount;
    }
 
    public void setMaxEligibleAmount(Double maxEligibleAmount) {
        this.maxEligibleAmount = maxEligibleAmount;
    }
}

