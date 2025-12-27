package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {
    
    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;
    
    public FinancialProfileServiceImpl(FinancialProfileRepository financialProfileRepository, UserRepository userRepository) {
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        // Validate user exists
        User user = userRepository.findById(profile.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Validate credit score
        if (profile.getCreditScore() < 300 || profile.getCreditScore() > 900) {
            throw new BadRequestException("creditScore must be between 300 and 900");
        }
        
        // Validate monthly income
        if (profile.getMonthlyIncome() <= 0) {
            throw new BadRequestException("Monthly income must be greater than 0");
        }
        
        // Check if profile already exists for this user
        Optional<FinancialProfile> existing = financialProfileRepository.findByUserId(user.getId());
        if (existing.isPresent()) {
            // Update existing profile
            FinancialProfile existingProfile = existing.get();
            existingProfile.setMonthlyIncome(profile.getMonthlyIncome());
            existingProfile.setMonthlyExpenses(profile.getMonthlyExpenses());
            existingProfile.setExistingLoanEmi(profile.getExistingLoanEmi());
            existingProfile.setCreditScore(profile.getCreditScore());
            existingProfile.setSavingsBalance(profile.getSavingsBalance());
            return financialProfileRepository.save(existingProfile);
        }
        
        profile.setUser(user);
        return financialProfileRepository.save(profile);
    }
    
    @Override
    public FinancialProfile getByUserId(Long userId) {
        return financialProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
    }
}