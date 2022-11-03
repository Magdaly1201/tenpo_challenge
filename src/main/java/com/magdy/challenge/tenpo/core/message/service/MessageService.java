package com.magdy.challenge.tenpo.core.message.service;

import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.history.port.HistoryRepository;
import com.magdy.challenge.tenpo.core.message.port.MessageRepository;

import java.time.LocalDateTime;

public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository, HistoryRepository historyRepository) {
        this.messageRepository = messageRepository;
    }

    public void createMessage(String type, String userRequest, String payload, String status){
        messageRepository.createdMessage(new History(LocalDateTime.now(), type, payload, userRequest, status));
    }
}
