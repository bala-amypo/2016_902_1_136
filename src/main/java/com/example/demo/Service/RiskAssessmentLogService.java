package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;
import java.util.List;

public interface RiskAssessmentLogService {

    List<RiskAssessmentLog> getLogsByLoanRequest(Long loanRequestId);
}
