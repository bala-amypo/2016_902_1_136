package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/risk")
public class RiskAssessmentController {

    private final RiskAssessmentService service;

    public RiskAssessmentController(RiskAssessmentService service) {
        this.service = service;
    }

    @PostMapping("/{loanRequestId}")
    public ResponseEntity<RiskAssessment> assessRisk(
            @PathVariable Long loanRequestId) {

        return ResponseEntity.ok(
                service.assessRisk(loanRequestId)
        );
    }

    @GetMapping("/{loanRequestId}")
    public ResponseEntity<RiskAssessment> getRisk(
            @PathVariable Long loanRequestId) {

        return ResponseEntity.ok(
                service.getByLoanRequestId(loanRequestId)
        );
    }
}
