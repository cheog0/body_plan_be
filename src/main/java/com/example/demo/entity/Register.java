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
    private String userid;

    @Column(nullable = false, length = 500)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Double height;
    private Double weight;

    public Register(RegisterRequestDto dto) {
        this.userid = dto.getUserid();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.gender = dto.getGender();
        this.birthDate = dto.getBirthDate();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.address = dto.getAddress();
        this.nickname = dto.getNickname();
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
    }
}
