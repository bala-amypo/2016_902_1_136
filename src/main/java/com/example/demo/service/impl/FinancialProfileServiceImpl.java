package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {
    
    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public FinancialProfileServiceImpl(FinancialProfileRepository financialProfileRepository, 
                                       UserRepository userRepository) {
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public FinancialProfile createOrUpdate(FinancialProfile financialProfile) {
        User user = userRepository.findById(financialProfile.getUser().getId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (financialProfile.getCreditScore() < 300 || financialProfile.getCreditScore() > 900) {
            throw new BadRequestException("Credit score must be between 300 and 900");
        }
        
        // Check if profile exists for user
        financialProfileRepository.findByUserId(user.getId())
            .ifPresent(existing -> financialProfile.setId(existing.getId()));
        
        financialProfile.setUser(user);
        return financialProfileRepository.save(financialProfile);
    }
    
    @Override
    public FinancialProfile getByUserId(Long userId) {
        return financialProfileRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found for user id: " + userId));
    }
}