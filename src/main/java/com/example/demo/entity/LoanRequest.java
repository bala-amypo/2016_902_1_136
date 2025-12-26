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
    private User user;

    private Double requestedAmount;
    private Integer tenureMonths;
    private String status;
    private LocalDateTime submittedAt;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    // getters & setters
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }

    public Double getRequestedAmount() {
        return requestedAmount;
    }
 
    public void setRequestedAmount(Double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Integer getTenureMonths() {
        return tenureMonths;
    }
 
    public void setTenureMonths(Integer tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
 
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
 }

