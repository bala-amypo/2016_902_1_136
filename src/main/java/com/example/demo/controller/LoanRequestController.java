package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanRequestController {

    @Autowired
    private LoanRequestService loanService;

    @PostMapping
    public ResponseEntity<LoanRequest> applyLoan(@RequestBody LoanDtos dto) {

        LoanRequest loan = loanService.createLoan(dto);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanRequest> getLoan(@PathVariable Long id) {

        return ResponseEntity.ok(loanService.getById(id));
    }
}
