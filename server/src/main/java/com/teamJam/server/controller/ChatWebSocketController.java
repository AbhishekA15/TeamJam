package com.teamJam.server.controller;

import com.teamJam.server.dto.ChatMessage;
import com.teamJam.server.dto.MessageDTO;
import com.teamJam.server.entity.Message;
import com.teamJam.server.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage chatMessage, Principal principal) {

        String username = principal.getName(); // 🔥 from JWT

        Message savedMessage = messageService.sendMessageFromUsername(
                username,
                chatMessage
        );

        messagingTemplate.convertAndSend(
                "/topic/messages/" + savedMessage.getReceiver().getId(),
                savedMessage
        );
    }

    private MessageDTO convertToDTO(ChatMessage chatMessage) {
        MessageDTO dto = new MessageDTO();
        dto.setReceiverId(chatMessage.getReceiverId());
        dto.setContent(chatMessage.getContent());
        return dto;
    }
}
