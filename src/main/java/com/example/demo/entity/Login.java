package com.example.demo.entity;

import com.example.demo.dto.LoginRequestDto;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "users") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    // 생성자: LoginRequestDto 객체를 받아서 userid와 password를 초기화
    public Login(LoginRequestDto requestDto) {
        this.userid = requestDto.getUserid();
        this.password = requestDto.getPassword();
    }

    // update 메서드: 기존 객체의 password 값을 수정
    public void update(LoginRequestDto requestDto) {
        this.password = requestDto.getPassword();  // getContents() -> getPassword()로 수정
    }
}
