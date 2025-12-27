package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "financial_profiles")
public class FinancialProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private Double monthlyIncome;
    
    @Column(nullable = false)
    private Double monthlyExpenses;
    
    private Double existingLoanEmi = 0.0;
    
    @Column(nullable = false)
    private Integer creditScore;
    
    @Column(nullable = false)
    private Double savingsBalance;
    
    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt;
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdatedAt = LocalDateTime.now();
    }
    
    public FinancialProfile() {}
    
    public FinancialProfile(User user, Double monthlyIncome, Double monthlyExpenses, Integer creditScore, Double savingsBalance) {
        this.user = user;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenses = monthlyExpenses;
        this.creditScore = creditScore;
        this.savingsBalance = savingsBalance;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(Double monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    
    public Double getMonthlyExpenses() { return monthlyExpenses; }
    public void setMonthlyExpenses(Double monthlyExpenses) { this.monthlyExpenses = monthlyExpenses; }
    
    public Double getExistingLoanEmi() { return existingLoanEmi; }
    public void setExistingLoanEmi(Double existingLoanEmi) { this.existingLoanEmi = existingLoanEmi; }
    
    public Integer getCreditScore() { return creditScore; }
    public void setCreditScore(Integer creditScore) { this.creditScore = creditScore; }
    
    public Double getSavingsBalance() { return savingsBalance; }
    public void setSavingsBalance(Double savingsBalance) { this.savingsBalance = savingsBalance; }
    
    public LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }
    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }
}