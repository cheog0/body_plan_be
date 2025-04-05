package com.example.demo.controller;

import com.example.demo.dto.UserUpdateRequestDto;
import com.example.demo.entity.Register;
import com.example.demo.service.UserUpdateService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserUpdateController {

    private final UserUpdateService userUpdateService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserUpdateController(UserUpdateService userUpdateService, JwtUtil jwtUtil) {
        this.userUpdateService = userUpdateService;
        this.jwtUtil = jwtUtil;
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateMyInfo(@RequestHeader("Authorization") String authHeader,
                                          @RequestBody UserUpdateRequestDto dto) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Invalid token format");
        }

        String token = authHeader.substring(7);
        String userid = jwtUtil.validateToken(token);

        if (userid == null) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        Register updatedUser = userUpdateService.updateUser(userid, dto);
        if (updatedUser == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("nickname", updatedUser.getNickname());
        response.put("height", updatedUser.getHeight());
        response.put("weight", updatedUser.getWeight());
        response.put("birthDate", updatedUser.getBirthDate());

        return ResponseEntity.ok(response);
    }
}
