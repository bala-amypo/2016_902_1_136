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
//      }

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

// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class EligibilityResult {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @OneToOne
//     private LoanRequest loanRequest;

//     private Boolean eligible;
//     private Double maxEligibleAmount;

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

//     public Boolean getEligible() {
//         return eligible;
//     }
 
//     public void setEligible(Boolean eligible) {
//         this.eligible = eligible;
//     }

//     public Double getMaxEligibleAmount() {
//         return maxEligibleAmount;
//     }
 
//     public void setMaxEligibleAmount(Double maxEligibleAmount) {
//         this.maxEligibleAmount = maxEligibleAmount;
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_results")
@Data
public class EligibilityResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_request_id", nullable = false, unique = true)
    private LoanRequest loanRequest;
    
    @Column(name = "is_eligible", nullable = false)
    private Boolean isEligible;
    
    @Column(name = "max_eligible_amount", nullable = false)
    private Double maxEligibleAmount;
    
    @Column(name = "recommended_tenure_months")
    private Integer recommendedTenureMonths;
    
    @Column(name = "monthly_emi")
    private Double monthlyEmi;
    
    @Column(name = "reason")
    private String reason;
    
    @CreationTimestamp
    @Column(name = "calculated_at", updatable = false)
    private LocalDateTime calculatedAt;
}