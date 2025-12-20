package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    @Override
    public LoanRequest submitLoanRequest(LoanRequest request) {
        return request;
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return Collections.emptyList();
    }

    @Override
    public LoanRequest getRequestById(Long id) {
        return null;
    }

    @Override
    public List<LoanRequest> getAllRequests() {
        return Collections.emptyList();
    }
}
