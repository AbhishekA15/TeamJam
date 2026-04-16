package com.teamJam.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private Long receiverId;
    private String content;
}