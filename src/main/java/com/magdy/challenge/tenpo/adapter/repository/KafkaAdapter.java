package com.magdy.challenge.tenpo.adapter.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.message.port.MessageRepository;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaAdapter implements MessageRepository {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC;

    public KafkaAdapter(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate, String TOPIC) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.TOPIC = TOPIC;
    }

    @Override
    public void createdMessage(History history) {
        try {
            kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(history));
        } catch (JsonProcessingException e) {
            //TODO: Add exception
            e.printStackTrace();
        }
    }
}
