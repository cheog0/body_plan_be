package com.example.demo.controller;

import com.example.demo.entity.Register;
import com.example.demo.service.UserInfoService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserInfoController(UserInfoService userInfoService, JwtUtil jwtUtil) {
        this.userInfoService = userInfoService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Invalid token format");
        }

        String token = authHeader.substring(7);
        String userid = jwtUtil.validateToken(token);

        if (userid == null) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        Register user = userInfoService.getUserByUserid(userid);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // 필요한 정보만 추려서 응답
        Map<String, Object> response = new HashMap<>();
        response.put("nickname", user.getNickname());
        response.put("height", user.getHeight());
        response.put("weight", user.getWeight());
        response.put("birthDate", user.getBirthDate());

        return ResponseEntity.ok(response);
    }
}
