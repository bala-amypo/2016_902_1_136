package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;
import java.util.List;

public interface RiskAssessmentLogService {

    void logRisk(Long loanRequestId, String riskLevel);

    List<RiskAssessmentLog> getLogsByRequest(Long loanRequestId);
}
