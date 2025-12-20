package com.example.demo.service.impl;

import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    @Override
    public String getFinancialStatus(Long userId) {
        // Dummy logic
        return "GOOD";
    }
}
