package com.example.demo.entity;

public class EligibilityResult {

    private boolean eligible;
    private String message;

    public EligibilityResult() {}

    public EligibilityResult(boolean eligible, String message) {
        this.eligible = eligible;
        this.message = message;
    }

    public boolean isEligible() { return eligible; }
    public void setEligible(boolean eligible) { this.eligible = eligible; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
