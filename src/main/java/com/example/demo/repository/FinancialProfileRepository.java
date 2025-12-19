package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FinancialProfile;

@Repository
public interface FinancialProfileRepository extends JpaRepository<FinancialProfile,Integer>{

}