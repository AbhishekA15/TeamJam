package com.teamJam.server.controller;

import com.teamJam.server.dto.MessageDTO;
import com.teamJam.server.entity.Message;
import com.teamJam.server.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDTO dto) {
        return ResponseEntity.ok(messageService.sendMessage(dto));
    }
}