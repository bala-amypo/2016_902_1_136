package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;

public interface RiskAssessmentLogService {

    RiskAssessmentLog saveRiskLog(RiskAssessmentLog log);

    String evaluateRisk(int creditScore);
}
