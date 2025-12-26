// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class FinancialProfile {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private double income;
//     private double expenses;
//     private int creditScore;

//     public FinancialProfile() {}

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public double getIncome() { return income; }
//     public void setIncome(double income) { this.income = income; }

//     public double getExpenses() { return expenses; }
//     public void setExpenses(double expenses) { this.expenses = expenses; }

//     public int getCreditScore() { return creditScore; }
//     public void setCreditScore(int creditScore) { this.creditScore = creditScore; }
// }

// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// public class FinancialProfile {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @OneToOne
//     private User user;

//     private Double monthlyIncome;
//     private Double monthlyExpenses;
//     private Double existingLoanEmi;
//     private Integer creditScore;
//     private Double savingsBalance;

//     private LocalDateTime lastUpdatedAt;

//     @PrePersist
//     @PreUpdate
//     public void updateTimestamp() {
//         this.lastUpdatedAt = LocalDateTime.now();
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

//     public Double getMonthlyIncome() {
//         return monthlyIncome;
//     }
 
//     public void setMonthlyIncome(Double monthlyIncome) {
//         this.monthlyIncome = monthlyIncome;
//     }

//     public Double getMonthlyExpenses() {
//         return monthlyExpenses;
//     }
 
//     public void setMonthlyExpenses(Double monthlyExpenses) {
//         this.monthlyExpenses = monthlyExpenses;
//     }

//     public Double getExistingLoanEmi() {
//         return existingLoanEmi;
//     }
 
//     public void setExistingLoanEmi(Double existingLoanEmi) {
//         this.existingLoanEmi = existingLoanEmi;
//     }

//     public Integer getCreditScore() {
//         return creditScore;
//     }
 
//     public void setCreditScore(Integer creditScore) {
//         this.creditScore = creditScore;
//     }

//     public Double getSavingsBalance() {
//         return savingsBalance;
//     }
 
//     public void setSavingsBalance(Double savingsBalance) {
//         this.savingsBalance = savingsBalance;
//     }

//     public LocalDateTime getLastUpdatedAt() {
//         return lastUpdatedAt;
//     }
 
//     public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
//         this.lastUpdatedAt = lastUpdatedAt;
//     }
//  }

package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "financial_profiles")
@Data
public class FinancialProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    
    @NotNull(message = "Monthly income is required")
    @PositiveOrZero(message = "Monthly income must be zero or positive")
    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;
    
    @NotNull(message = "Monthly expenses are required")
    @PositiveOrZero(message = "Monthly expenses must be zero or positive")
    @Column(name = "monthly_expenses", nullable = false)
    private Double monthlyExpenses;
    
    @PositiveOrZero(message = "Existing loan EMI must be zero or positive")
    @Column(name = "existing_loan_emi")
    private Double existingLoanEmi = 0.0;
    
    @NotNull(message = "Credit score is required")
    @Min(value = 300, message = "Credit score must be between 300 and 900")
    @Max(value = 900, message = "Credit score must be between 300 and 900")
    @Column(name = "credit_score", nullable = false)
    private Integer creditScore;
    
    @NotNull(message = "Savings balance is required")
    @PositiveOrZero(message = "Savings balance must be zero or positive")
    @Column(name = "savings_balance", nullable = false)
    private Double savingsBalance;
    
    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
    
    @PrePersist
    @PreUpdate
    public void validate() {
        if (this.existingLoanEmi == null) {
            this.existingLoanEmi = 0.0;
        }
    }
}