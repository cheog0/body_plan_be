package com.example.demo.controller;

import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequestDto) {
        try {
            boolean isRegistered = registerService.register(registerRequestDto);
            if (isRegistered) {
                return ResponseEntity.ok("회원가입 성공!");
            } else {
                return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean exists = registerService.existsByNickname(nickname);
        return ResponseEntity.ok(exists);
    }
}