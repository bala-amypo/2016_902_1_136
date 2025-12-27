package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_requests")
public class LoanRequest {
    
    public enum Status {
        PENDING, APPROVED, REJECTED
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private Double requestedAmount;
    
    @Column(nullable = false)
    private Integer tenureMonths;
    
    private String purpose;
    
    @Column(nullable = false)
    private String status = Status.PENDING.name();
    
    @Column(nullable = false)
    private LocalDateTime submittedAt;
    
    @PrePersist
    public void onCreate() {
        submittedAt = LocalDateTime.now();
        if (status == null) {
            status = Status.PENDING.name();
        }
    }
    
    public LoanRequest() {}
    
    public LoanRequest(User user, Double requestedAmount, Integer tenureMonths) {
        this.user = user;
        this.requestedAmount = requestedAmount;
        this.tenureMonths = tenureMonths;
        this.status = Status.PENDING.name();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Double getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(Double requestedAmount) { this.requestedAmount = requestedAmount; }
    
    public Integer getTenureMonths() { return tenureMonths; }
    public void setTenureMonths(Integer tenureMonths) { this.tenureMonths = tenureMonths; }
    
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    
    public LocalDateTime getAppliedAt() { return submittedAt; }
}