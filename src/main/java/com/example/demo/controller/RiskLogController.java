// package com.example.demo.controller;

// import com.example.demo.entity.RiskAssessmentLog;
// import com.example.demo.repository.RiskAssessmentLogRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/risk")
// public class RiskLogController {

//     @Autowired
//     private RiskAssessmentLogRepository logRepository;

//     @GetMapping
//     public List<RiskAssessmentLog> getAll() {
//         return logRepository.findAll();
//     }
// }


package com.example.demo.controller;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-logs")
public class RiskLogController {

    @Autowired
    private RiskAssessmentLogRepository repository;

    @GetMapping
    public List<RiskAssessmentLog> getAllLogs() {
        return repository.findAll();
    }
}
