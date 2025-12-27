package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk-logs")
@Tag(name = "RiskLog", description = "Risk assessment logs")
public class RiskLogController {
    
    private final RiskAssessmentService riskAssessmentService;
    
    public RiskLogController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }
    
    @PostMapping("/assess/{loanRequestId}")
    public ResponseEntity<RiskAssessment> assessRisk(@PathVariable Long loanRequestId) {
        RiskAssessment assessment = riskAssessmentService.assessRisk(loanRequestId);
        return ResponseEntity.ok(assessment);
    }
    
    @GetMapping("/{loanRequestId}")
    public ResponseEntity<RiskAssessment> getRiskAssessment(@PathVariable Long loanRequestId) {
        RiskAssessment assessment = riskAssessmentService.getByLoanRequestId(loanRequestId);
        return ResponseEntity.ok(assessment);
    }
}