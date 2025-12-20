package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RiskAssessmentLogServiceImpl implements RiskAssessmentLogService {

    @Override
    public void logRisk(Long loanRequestId, String riskLevel) {
        System.out.println("Risk logged");
    }

    @Override
    public List<RiskAssessmentLog> getLogsByRequest(Long loanRequestId) {
        return Collections.emptyList();
    }
}
