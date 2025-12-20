package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    @Override
    public FinancialProfile createOrUpdateProfile(FinancialProfile profile) {
        return profile;
    }

    @Override
    public FinancialProfile getProfileByUser(Long userId) {
        return null;
    }
}
