package com.example.slack.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatResponseDto {

    private final Long workId;
    private final String nickname;
    private final String chat;
    private final String memberNickname;
    private final LocalDateTime createAt;

    public ChatResponseDto(Long workId, String nickname, String chat, String memberNickname, LocalDateTime createdAt) {
        this.workId = workId;
        this.nickname = nickname;
        this.chat = chat;
        this.memberNickname = memberNickname;
        this.createAt = createdAt;
    }
}