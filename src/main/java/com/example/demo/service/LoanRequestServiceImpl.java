package com.example.demo.service.impl;

import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    @Override
    public String submitLoanRequest(Long userId, Double amount) {
        return "Loan request submitted for user " + userId;
    }
}
