package com.example.demo.controller;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-logs")
@Tag(name = "Risk Logs")
public class RiskLogController {

    private final RiskAssessmentLogService logService;

    public RiskLogController(RiskAssessmentLogService logService) {
        this.logService = logService;
    }

    @GetMapping("/{loanRequestId}")
    public List<RiskAssessmentLog> getLogs(@PathVariable Long loanRequestId) {
        return logService.getLogsByRequest(loanRequestId);
    }
}
