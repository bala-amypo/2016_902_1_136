package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {
    
    private final LoanRequestRepository loanRequestRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public LoanRequestServiceImpl(LoanRequestRepository loanRequestRepository, 
                                  UserRepository userRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public LoanRequest submitRequest(LoanRequest loanRequest) {
        User user = userRepository.findById(loanRequest.getUser().getId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (loanRequest.getRequestedAmount() <= 0) {
            throw new BadRequestException("Requested amount must be greater than 0");
        }
        
        if (loanRequest.getTenureMonths() <= 0) {
            throw new BadRequestException("Tenure must be at least 1 month");
        }
        
        loanRequest.setUser(user);
        return loanRequestRepository.save(loanRequest);
    }
    
    @Override
    public LoanRequest getById(Long id) {
        return loanRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Loan request not found with id: " + id));
    }
    
    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return loanRequestRepository.findByUserId(userId);
    }
}