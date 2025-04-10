package com.example.demo.entity;

import com.example.demo.dto.RegisterRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userid; // 이메일 형식

    @Column(nullable = false, length = 500)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Double height;
    private Double weight;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String birthDate;

    public Register(RegisterRequestDto dto) {
        this.userid = dto.getUserid();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
        this.gender = dto.getGender();
        this.birthDate = dto.getBirthDate();
    }
}