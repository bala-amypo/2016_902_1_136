package com.example.demo.service;

import com.example.demo.entity.LoanRequest;

import java.util.List;

public interface LoanRequestService {

    LoanRequest applyLoan(LoanRequest loanRequest);

    List<LoanRequest> getLoansByUserId(Long userId);
}
