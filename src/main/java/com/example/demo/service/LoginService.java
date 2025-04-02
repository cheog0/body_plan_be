package com.example.demo.service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // 로그인 로직 처리
    public boolean login(LoginRequestDto loginRequestDto) {
        // 사용자 정보 조회
        Login user = loginRepository.findByUserid(loginRequestDto.getUserid());

        // 사용자 존재 여부와 비밀번호 일치 여부 확인
        if (user != null && user.getPassword().equals(loginRequestDto.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
