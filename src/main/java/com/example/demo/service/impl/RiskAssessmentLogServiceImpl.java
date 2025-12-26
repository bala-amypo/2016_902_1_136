package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentLogRepository;
import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RiskAssessmentLogServiceImpl
        implements RiskAssessmentLogService {

    @Autowired
    private RiskAssessmentLogRepository repository;

    @Override
    public RiskAssessmentLog save(RiskAssessmentLog log) {
        log.setCreatedAt(LocalDateTime.now());
        return repository.save(log);
    }

    @Override
    public List<RiskAssessmentLog> getAll() {
        return repository.findAll();
    }
}
