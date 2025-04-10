package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String userid; // 이메일 형식
    private String password;
    private String nickname;
    private Double height;
    private Double weight;
    private String gender;
    private String birthDate;
}
