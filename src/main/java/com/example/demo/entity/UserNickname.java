package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users") // 너가 사용하는 DB 테이블명으로 바꿔도 됨
@Getter
@Setter
@NoArgsConstructor
public class UserNickname {

    @Id
    private String userid;

    private String nickname;

    // 생성자, 기타 필드는 필요에 따라 추가 가능
}
