package com.example.demo.service.impl;

import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentLogServiceImpl implements RiskAssessmentLogService {

    @Override
    public void logRisk(Long loanRequestId, String riskLevel) {
        System.out.println("Risk logged: " + loanRequestId + " | " + riskLevel);
    }
}
