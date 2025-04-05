package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String nickname;
    private Double height;
    private Double weight;
    private String birthDate;
}
