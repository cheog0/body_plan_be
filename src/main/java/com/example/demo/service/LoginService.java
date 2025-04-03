package com.example.demo.service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.util.JwtUtil; // 추가
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final JwtUtil jwtUtil; // 추가

    @Autowired
    public LoginService(LoginRepository loginRepository, JwtUtil jwtUtil) { // 추가
        this.loginRepository = loginRepository;
        this.jwtUtil = jwtUtil; // 추가
    }

    // ✅ 로그인 시 JWT 반환
    public String login(LoginRequestDto loginRequestDto) {
        Optional<Login> userOptional = loginRepository.findByUserid(loginRequestDto.getUserid());

        return userOptional
                .filter(user -> user.getPassword().equals(loginRequestDto.getPassword()))
                .map(user -> jwtUtil.generateToken(user.getUserid())) // JWT 생성 후 반환
                .orElse(null); // 로그인 실패 시 null 반환
    }
}
