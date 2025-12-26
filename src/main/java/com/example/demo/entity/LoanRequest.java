// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class LoanRequest {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private double amount;
//     private int tenure;
//     private String status;

//     public LoanRequest() {}

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public double getAmount() { return amount; }
//     public void setAmount(double amount) { this.amount = amount; }

//     public int getTenure() { return tenure; }
//     public void setTenure(int tenure) { this.tenure = tenure; }

//     public String getStatus() { return status; }
//     public void setStatus(String status) { this.status = status; }
//  }

// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// public class LoanRequest {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private User user;

//     private Double requestedAmount;
//     private Integer tenureMonths;
//     private String status;
//     private LocalDateTime submittedAt;

//     public enum Status {
//         PENDING, APPROVED, REJECTED
//     }

//     // getters & setters
//     public Long getId() {
//         return id;
//     }
 
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public User getUser() {
//         return user;
//     }
 
//     public void setUser(User user) {
//         this.user = user;
//     }

//     public Double getRequestedAmount() {
//         return requestedAmount;
//     }
 
//     public void setRequestedAmount(Double requestedAmount) {
//         this.requestedAmount = requestedAmount;
//     }

//     public Integer getTenureMonths() {
//         return tenureMonths;
//     }
 
//     public void setTenureMonths(Integer tenureMonths) {
//         this.tenureMonths = tenureMonths;
//     }

//     public String getStatus() {
//         return status;
//     }
 
//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public LocalDateTime getSubmittedAt() {
//         return submittedAt;
//     }
 
//     public void setSubmittedAt(LocalDateTime submittedAt) {
//         this.submittedAt = submittedAt;
//     }
//  }

package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan_requests")
@Data
public class LoanRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotNull(message = "Requested amount is required")
    @Min(value = 1, message = "Requested amount must be greater than 0")
    @Column(name = "requested_amount", nullable = false)
    private Double requestedAmount;
    
    @NotNull(message = "Tenure is required")
    @Min(value = 1, message = "Tenure must be at least 1 month")
    @Column(name = "tenure_months", nullable = false)
    private Integer tenureMonths;
    
    @Column(nullable = false)
    private String status = Status.PENDING.name();
    
    @CreationTimestamp
    @Column(name = "submitted_at", updatable = false)
    private LocalDateTime submittedAt;
    
    @OneToOne(mappedBy = "loanRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EligibilityResult eligibilityResult;
    
    @OneToOne(mappedBy = "loanRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RiskAssessment riskAssessment;
    
    public enum Status {
        PENDING, APPROVED, REJECTED, PROCESSING
    }
    
    @PrePersist
    public void setDefaults() {
        if (this.status == null) {
            this.status = Status.PENDING.name();
        }
    }
}
