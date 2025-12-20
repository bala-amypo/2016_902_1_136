package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    @Autowired
    private LoanRequestRepository loanRepository;

    @PostMapping
    public LoanRequest submit(@RequestBody LoanRequest request) {
        return loanRepository.save(request);
    }

    @GetMapping
    public List<LoanRequest> getAll() {
        return loanRepository.findAll();
    }
}
