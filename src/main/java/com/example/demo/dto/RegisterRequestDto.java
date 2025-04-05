package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String userid;
    private String password;
    private String name;
    private String gender;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String nickname;
    private Double height;
    private Double weight;
}
