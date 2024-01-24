package com.harshproject.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harshproject.entity.UserInfo;
import com.harshproject.repository.UserInfoRepository;

@Service
public class AuthService {

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User added to system.";
    }
}
