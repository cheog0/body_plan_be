package com.example.demo.dto;

import com.example.demo.entity.UserNickname;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResponseDto {
    private String userid;
    private String nickname;

    public UserSearchResponseDto(UserNickname userNickname) {
        this.userid = userNickname.getUserid();
        this.nickname = userNickname.getNickname();
    }
}
