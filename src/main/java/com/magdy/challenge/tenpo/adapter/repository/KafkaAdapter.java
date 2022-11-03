package com.magdy.challenge.tenpo.adapter.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.message.port.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaAdapter implements MessageRepository {

    private final Gson gson;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HistoryService historyService;

    public KafkaAdapter(Gson gson, KafkaTemplate<String, String> kafkaTemplate, String TOPIC, HistoryService historyService) {
        this.gson = gson;
        this.kafkaTemplate = kafkaTemplate;
        this.TOPIC = TOPIC;
        this.historyService = historyService;
    }

    @Override
    public void createdMessage(History history) {
        kafkaTemplate.send(TOPIC, gson.toJson(history));
    }

    @Override
    @KafkaListener(topics="history-events")
    public void consumeMessage(String message) {
        logger.info("#### -> Consumed message -> %s%n", message);
        historyService.createTransaction(gson.fromJson(message,History.class));
    }
}
