// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.security.JwtUtil;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserRepository userRepository;
//     private final JwtUtil jwtUtil;
//     private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//     public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
//         this.userRepository = userRepository;
//         this.jwtUtil = jwtUtil;
//     }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

//         User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("Invalid email"));

//         if (!encoder.matches(request.getPassword(), user.getPassword())) {
//             throw new RuntimeException("Invalid password");
//         }

//         String token = jwtUtil.generateToken(
//                 java.util.Map.of(
//                         "email", user.getEmail(),
//                         "role", user.getRole(),
//                         "userId", user.getId()
//                 ),
//                 user.getEmail()
//         );

//         return ResponseEntity.ok(
//                 new AuthResponse(token, user.getEmail(), user.getRole())
//         );
//     }
// }
