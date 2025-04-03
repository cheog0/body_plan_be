package com.example.demo.service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // 로그인 로직 처리
    public boolean login(LoginRequestDto loginRequestDto) {
        Optional<Login> userOptional = loginRepository.findByUserid(loginRequestDto.getUserid());

        return userOptional
                .map(user -> user.getPassword().equals(loginRequestDto.getPassword()))
                .orElse(false);
    }
}