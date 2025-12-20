package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-requests")
@Tag(name = "Loan Requests")
public class LoanRequestController {

    private final LoanRequestService loanService;

    public LoanRequestController(LoanRequestService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public LoanRequest submit(@RequestBody LoanRequest request) {
        return loanService.submitLoanRequest(request);
    }

    @GetMapping("/user/{userId}")
    public List<LoanRequest> getByUser(@PathVariable Long userId) {
        return loanService.getRequestsByUser(userId);
    }

    @GetMapping("/{id}")
    public LoanRequest getById(@PathVariable Long id) {
        return loanService.getRequestById(id);
    }

    @GetMapping
    public List<LoanRequest> getAll() {
        return loanService.getAllRequests();
    }
}
