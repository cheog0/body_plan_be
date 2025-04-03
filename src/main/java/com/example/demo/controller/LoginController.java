package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    // ✅ 로그인 요청 시 JWT 반환
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = loginService.login(loginRequestDto);

        if (token != null) {
            return ResponseEntity.ok(token); // 성공 시 JWT 반환
        } else {
            return ResponseEntity.status(401).body("Invalid credentials"); // 실패 시 401 응답
        }
    }
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Invalid token format");
        }

        String token = authHeader.substring(7); // "Bearer " 제거
        String userid = jwtUtil.validateToken(token);

        if (userid != null) {
            return ResponseEntity.ok("토큰 유효: " + userid);
        } else {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}
