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

@Entity
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double income;
    private double loanAmount;
    private int tenure;
    private String purpose;
    private String status;

    // Getters
    public Long getId() {
        return id;
    }

    public double getIncome() {
        return income;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public int getTenure() {
        return tenure;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setIncome(double income) {
        this.income = income;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
