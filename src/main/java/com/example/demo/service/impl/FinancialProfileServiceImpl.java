package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinancialProfileServiceImpl
        implements FinancialProfileService {

    private final FinancialProfileRepository repository;
    private final UserRepository userRepository;

    public FinancialProfileServiceImpl(
            FinancialProfileRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {

        if (profile.getCreditScore() < 300 ||
            profile.getCreditScore() > 900) {
            throw new BadRequestException("Invalid credit score");
        }

        Long userId = profile.getUser().getId();

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Optional<FinancialProfile> existing =
                repository.findByUserId(userId);

        if (existing.isPresent()) {
            profile.setId(existing.get().getId());
        }

        return repository.save(profile);
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found"));
    }
}
