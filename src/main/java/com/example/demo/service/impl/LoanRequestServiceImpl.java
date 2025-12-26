package com.example.demo.service.impl;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    @Autowired
    private LoanRequestRepository repository;

    @Override
    public LoanRequest createLoan(LoanDtos dto) {

        LoanRequest loan = new LoanRequest();
        loan.setIncome(dto.getIncome());
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setTenure(dto.getTenure());
        loan.setPurpose(dto.getPurpose());
        loan.setStatus("PENDING");

        return repository.save(loan);
    }

    @Override
    public LoanRequest getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }
}
