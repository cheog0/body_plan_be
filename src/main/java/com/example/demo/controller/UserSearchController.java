package com.example.demo.controller;

import com.example.demo.dto.UserSearchResponseDto;
import com.example.demo.entity.UserNickname;
import com.example.demo.service.UserSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserSearchController {

    private final UserSearchService userSearchService;

    public UserSearchController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping("/search-nickname")
    public ResponseEntity<?> searchUserByNickname(@RequestParam String nickname) {
        Optional<UserNickname> userOptional = userSearchService.findByNickname(nickname);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(new UserSearchResponseDto(userOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }
}
