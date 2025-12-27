package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanRequestService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestServiceImpl
        implements LoanRequestService {

    private final LoanRequestRepository repository;
    private final UserRepository userRepository;

    public LoanRequestServiceImpl(
            LoanRequestRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public LoanRequest submitRequest(LoanRequest request) {

        if (request.getRequestedAmount() <= 0) {
            throw new BadRequestException("Invalid loan amount");
        }

        userRepository.findById(request.getUser().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return repository.save(request);
    }

    @Override
    public LoanRequest getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loan not found"));
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
   }
