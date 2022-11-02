package com.magdy.challenge.tenpo.infrastructure.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topics.tenpo.challenge.history}")
    private String TOPIC;

    @Bean
    public NewTopic HistoryEvents() {
        return TopicBuilder.name(TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }



}
