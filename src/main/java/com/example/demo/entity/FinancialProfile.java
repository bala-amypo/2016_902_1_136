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

