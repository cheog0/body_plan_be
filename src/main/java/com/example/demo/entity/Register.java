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
public class Register {  // Login 대신 User 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", nullable = false, unique = true)
    private String userid;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    public Register(RegisterRequestDto requestDto) {
        this.userid = requestDto.getUserid();
        this.password = requestDto.getPassword();
        this.name = requestDto.getName();
        this.gender = requestDto.getGender();
        this.birthDate = requestDto.getBirthDate();
        this.email = requestDto.getEmail();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.address = requestDto.getAddress();
    }
}
