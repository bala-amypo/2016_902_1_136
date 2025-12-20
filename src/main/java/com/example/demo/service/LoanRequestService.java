package com.example.demo.service;

import com.example.demo.entity.LoanRequest;

public interface LoanRequestService {

    LoanRequest applyLoan(LoanRequest loanRequest);

    LoanRequest getLoanStatus(Long loanId);
}
