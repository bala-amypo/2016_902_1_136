package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    @Override
    public LoanRequest submitLoanRequest(LoanRequest request) {
        // TODO: implement saving logic
        return request;
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return new ArrayList<>(); // placeholder
    }

    @Override
    public LoanRequest getRequestById(Long id) {
        return new LoanRequest(); // placeholder
    }

    @Override
    public List<LoanRequest> getAllRequests() {
        return new ArrayList<>(); // placeholder
    }
}
