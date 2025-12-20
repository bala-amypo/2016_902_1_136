package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;

public interface FinancialProfileService {

    FinancialProfile saveOrUpdateProfile(FinancialProfile profile);

    FinancialProfile getProfileByUserId(Long userId);
}
