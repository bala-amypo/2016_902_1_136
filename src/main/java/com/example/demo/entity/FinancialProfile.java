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

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "user_id")
)
public class FinancialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double monthlyIncome;
    private Double monthlyExpenses;
    private Double existingLoanEmi;
    private Integer creditScore;
    private Double savingsBalance;

    private LocalDateTime lastUpdatedAt;

    // Getters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public Double getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public Double getExistingLoanEmi() {
        return existingLoanEmi;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public Double getSavingsBalance() {
        return savingsBalance;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    // Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setMonthlyExpenses(Double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public void setExistingLoanEmi(Double existingLoanEmi) {
        this.existingLoanEmi = existingLoanEmi;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public void setSavingsBalance(Double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
