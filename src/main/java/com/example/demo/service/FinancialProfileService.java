package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;

@Service
public interface FinancialProfileService {
    FinancialProfile createOrUpdate(FinancialProfile profile);
    FinancialProfile getByUserId(Long userId);
}