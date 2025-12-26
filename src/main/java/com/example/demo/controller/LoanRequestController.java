package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-requests")
public class LoanRequestController {
    
    @Autowired
    private LoanRequestService loanRequestService;
    
    @PostMapping
    public ResponseEntity<LoanRequest> submitRequest(@RequestBody LoanRequest loanRequest) {
        LoanRequest result = loanRequestService.submitRequest(loanRequest);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LoanRequest> getById(@PathVariable Long id) {
        LoanRequest loanRequest = loanRequestService.getById(id);
        return ResponseEntity.ok(loanRequest);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanRequest>> getByUser(@PathVariable Long userId) {
        List<LoanRequest> requests = loanRequestService.getRequestsByUser(userId);
        return ResponseEntity.ok(requests);
    }
}