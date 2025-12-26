// package com.example.demo.repository;

// import com.example.demo.entity.RiskAssessmentLog;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface RiskAssessmentLogRepository
//         extends JpaRepository<RiskAssessmentLog, Long> {
// }

// package com.example.demo.repository;

// import com.example.demo.entity.RiskAssessment;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {

//     Optional<RiskAssessment> findByLoanRequestId(Long loanRequestId);
// }

package com.example.demo.repository;

import com.example.demo.entity.RiskAssessmentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskAssessmentLogRepository
        extends JpaRepository<RiskAssessmentLog, Long> {
}
