// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true, nullable = false)
//     private String username;

//     @Column(nullable = false)
//     private String password;

//     private String role;

//     public User() {}

//     public User(String username, String password, String role) {
//         this.username = username;
//         this.password = password;
//         this.role = role;
//      }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getUsername() { return username; }
//     public void setUsername(String username) { this.username = username; }

//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }

//     public String getRole() { return role; }
//     public void setRole(String role) { this.role = role; }
//  }

// package com.example.demo.entity;

//  import jakarta.persistence.*;

// @Entity
//  public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String email;
//     private String password;
//     private String fullName;
//     private String role;

//     public enum Role {
//         CUSTOMER, ADMIN
//      }

//     // getters & setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//      }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//      }

//     public String getPassword() {
//         return password;
//     }
 
//     public void setPassword(String password) {
//         this.password = password;
//      }

//     public String getFullName() {
//         return fullName;
//     }
 
//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//      }

//     public String getRole() {
//         return role;
//     }
 
//     public void setRole(String role) {
//         this.role = role;
//     }
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Full name is required")
    @Column(nullable = false)
    private String fullName;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role = Role.CUSTOMER.name();
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FinancialProfile financialProfile;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanRequest> loanRequests = new ArrayList<>();
    
    public enum Role {
        CUSTOMER, ADMIN
    }
    
    @PrePersist
    @PreUpdate
    public void validate() {
        if (this.role == null) {
            this.role = Role.CUSTOMER.name();
        }
    }
}