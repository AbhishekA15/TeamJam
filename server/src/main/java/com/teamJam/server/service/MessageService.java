package com.teamJam.server.service;

import com.teamJam.server.dto.MessageDTO;
import com.teamJam.server.entity.Message;
import com.teamJam.server.entity.User;
import com.teamJam.server.repository.MessageRepository;
import com.teamJam.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Message sendMessage(MessageDTO dto) {
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message message = Message.builder()
                .content(dto.getContent())
                .sender(sender)
                .receiver(receiver)
                .build();

        return messageRepository.save(message);
    }
}
