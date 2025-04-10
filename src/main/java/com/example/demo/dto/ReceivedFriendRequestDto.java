package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReceivedFriendRequestDto {
    private Long id;
    private String senderId;
    private String senderNickname;
    private String status;
}
