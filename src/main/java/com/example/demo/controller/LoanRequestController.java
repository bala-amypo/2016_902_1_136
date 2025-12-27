package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-requests")
@Tag(name = "Loan Request", description = "Loan request management")
public class LoanRequestController {
    
    private final LoanRequestService loanRequestService;
    
    public LoanRequestController(LoanRequestService loanRequestService) {
        this.loanRequestService = loanRequestService;
    }
    
    @PostMapping
    public ResponseEntity<LoanRequest> submitRequest(@RequestBody LoanRequest request) {
        LoanRequest submitted = loanRequestService.submitRequest(request);
        return ResponseEntity.ok(submitted);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanRequest>> getRequestsByUser(@PathVariable Long userId) {
        List<LoanRequest> requests = loanRequestService.getRequestsByUser(userId);
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LoanRequest> getById(@PathVariable Long id) {
        LoanRequest request = loanRequestService.getById(id);
        return ResponseEntity.ok(request);
    }
    
    @GetMapping
    public ResponseEntity<List<LoanRequest>> getAllRequests() {
        List<LoanRequest> requests = loanRequestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }
}