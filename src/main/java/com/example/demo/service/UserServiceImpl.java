package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getUserRole(Long userId) {
        // Dummy logic
        return "USER";
    }
}


package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getUserRole(Long userId) {
        return "USER";
    }
}
