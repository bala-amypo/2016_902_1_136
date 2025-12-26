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

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double requestedAmount;
    private Integer tenureMonths;
    private String purpose;

    private String status; // PENDING / APPROVED / REJECTED
    private LocalDateTime appliedAt;

    // Getters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Double getRequestedAmount() {
        return requestedAmount;
    }

    public Integer getTenureMonths() {
        return tenureMonths;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    // Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setRequestedAmount(Double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public void setTenureMonths(Integer tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
