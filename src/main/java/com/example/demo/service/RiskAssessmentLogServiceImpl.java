package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentLogRepository;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentLogServiceImpl implements RiskAssessmentLogService {

    private final RiskAssessmentLogRepository repository;

    public RiskAssessmentLogServiceImpl(RiskAssessmentLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public RiskAssessmentLog saveRiskLog(RiskAssessmentLog log) {
        return repository.save(log);
    }

    @Override
    public String evaluateRisk(int creditScore) {

        if (creditScore >= 750) {
            return "LOW RISK";
        } else if (creditScore >= 650) {
            return "MEDIUM RISK";
        }
        return "HIGH RISK";
    }
}
