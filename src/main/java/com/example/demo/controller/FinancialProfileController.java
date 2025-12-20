package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class FinancialProfileController {

    @Autowired
    private FinancialProfileRepository profileRepository;

    @PostMapping
    public FinancialProfile save(@RequestBody FinancialProfile profile) {
        return profileRepository.save(profile);
    }

    @GetMapping("/{id}")
    public FinancialProfile get(@PathVariable Long id) {
        return profileRepository.findById(id).orElse(null);
    }
}
