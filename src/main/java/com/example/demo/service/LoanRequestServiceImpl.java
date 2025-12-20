package com.example.demo.service;

import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository repository;

    public LoanRequestServiceImpl(LoanRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoanRequest applyLoan(LoanRequest loanRequest) {
        loanRequest.setStatus("PENDING");
        return repository.save(loanRequest);
    }

    @Override
    public LoanRequest getLoanStatus(Long loanId) {
        return repository.findById(loanId).orElse(null);
    }
}
