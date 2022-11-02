package com.magdy.challenge.tenpo.infrastructure.app.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magdy.challenge.tenpo.adapter.delivery.SumEndpoints;
import com.magdy.challenge.tenpo.adapter.gateway.PercentageClientImpl;
import com.magdy.challenge.tenpo.adapter.repository.HistoryAdapterRepository;
import com.magdy.challenge.tenpo.adapter.repository.KafkaAdapter;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.message.service.MessageService;
import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;
import com.magdy.challenge.tenpo.core.sum.service.SumService;
import com.magdy.challenge.tenpo.infrastructure.repository.dao.HistoryDao;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
public class SpringDependenciesBean {

    private final HistoryDao historyDao;

    @Value("${spring.kafka.topics.tenpo.challenge.history}")
    private String TOPIC;

    @Value("${random.integer.host}")
    private String randomIntegerHost;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    public SpringDependenciesBean(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public HistoryAdapterRepository historyRepository() {
        return new HistoryAdapterRepository(historyDao, modelMapper());
    }

    @Bean
    public HistoryService historyService() {
        return new HistoryService(historyRepository());
    }

    @Bean
    public PercentageClientImpl percentageClient() {
        return new PercentageClientImpl(restTemplate(), randomIntegerHost);
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaAdapter messageRepository() {
        return new KafkaAdapter(objectMapper(), kafkaTemplate(), TOPIC);
    }

    @Bean
    public MessageService messageService() {
        return new MessageService(messageRepository());
    }

    @Bean
    public PercentageService percentageService() {
        return new PercentageService(percentageClient(), historyService(), messageService());
    }

    @Bean
    public SumService sumService() {
        return new SumService(percentageService(), messageService());
    }

    @Bean
    public SumEndpoints sumEndpoints() {
        return new SumEndpoints(sumService(), historyService());
    }
}
