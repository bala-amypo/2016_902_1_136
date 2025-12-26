package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
✔ Used in:

User registration

Login

Duplicate email check

✅ 2️⃣ FinancialProfileRepository
java
Copy code
package com.example.demo.repository;

import com.example.demo.entity.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancialProfileRepository
        extends JpaRepository<FinancialProfile, Long> {

    Optional<FinancialProfile> findByUserId(Long userId);
}
✔ Ensures:

One profile per user

✅ 3️⃣ LoanRequestRepository
java
Copy code
package com.example.demo.repository;

import com.example.demo.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRequestRepository
        extends JpaRepository<LoanRequest, Long> {

    List<LoanRequest> findByUserId(Long userId);
}
✔ Used to:

Fetch all requests by user

HQL simulation in tests

✅ 4️⃣ EligibilityResultRepository
java
Copy code
package com.example.demo.repository;

import com.example.demo.entity.EligibilityResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EligibilityResultRepository
        extends JpaRepository<EligibilityResult, Long> {

    Optional<EligibilityResult> findByLoanRequestId(Long loanRequestId);
}
✔ Ensures:

One eligibility result per loan request

