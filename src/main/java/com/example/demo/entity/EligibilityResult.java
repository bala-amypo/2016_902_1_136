package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "eligibility_result")
public class EligibilityResult {

    @Id
    private Long id;

    private String status;

    private Double amount;

    // 🔹 Constructors
    public EligibilityResult() {}

    public EligibilityResult(Long id, String status, Double amount) {
        this.id = id;
        this.status = status;
        this.amount = amount;
    }

    // 🔹 Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
