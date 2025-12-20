package com.example.demo.service;

public interface RiskAssessmentLogService {
    void logRisk(Long loanRequestId, String riskLevel);
}
