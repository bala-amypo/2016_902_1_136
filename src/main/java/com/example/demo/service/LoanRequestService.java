package com.example.demo.service;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;

public interface LoanRequestService {

    LoanRequest createLoan(LoanDtos dto);

    LoanRequest getById(Long id);
}
