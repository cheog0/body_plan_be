package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String userid;   // 사용자 아이디
    private String password; // 사용자 비밀번호

    // 기본 생성자, Getter, Setter는 Lombok을 사용하여 자동 생성됨
}
