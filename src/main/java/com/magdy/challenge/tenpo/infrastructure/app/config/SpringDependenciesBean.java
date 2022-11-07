package com.magdy.challenge.tenpo.infrastructure.app.config;

import com.google.gson.Gson;
import com.magdy.challenge.tenpo.adapter.delivery.SumEndpoints;
import com.magdy.challenge.tenpo.adapter.delivery.UserEndpoints;
import com.magdy.challenge.tenpo.adapter.gateway.PercentageClientImpl;
import com.magdy.challenge.tenpo.adapter.repository.HistoryAdapterRepository;
import com.magdy.challenge.tenpo.adapter.repository.KafkaAdapter;
import com.magdy.challenge.tenpo.adapter.repository.UserAdapterRepository;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.message.service.MessageService;
import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;
import com.magdy.challenge.tenpo.core.sum.service.SumService;
import com.magdy.challenge.tenpo.core.user.service.UserService;
import com.magdy.challenge.tenpo.infrastructure.repository.dao.HistoryDao;
import com.magdy.challenge.tenpo.infrastructure.repository.dao.UserDao;
import com.magdy.challenge.tenpo.infrastructure.security.GeneratorJWT;
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
    private final UserDao userDao;

    @Value("${spring.kafka.topics.tenpo.challenge.history}")
    private String TOPIC;

    @Value("${random.integer.host}")
    private String randomIntegerHost;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    public SpringDependenciesBean(HistoryDao historyDao, UserDao userDao) {
        this.historyDao = historyDao;
        this.userDao = userDao;
    }

    @Bean
    @Primary
    public Gson gson() {
        return new Gson();
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
        return new KafkaAdapter(gson(), kafkaTemplate(), TOPIC, historyService());
    }

    @Bean
    public MessageService messageService() {
        return new MessageService(messageRepository(), historyRepository());
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

    @Bean
    public UserEndpoints userEndpoints() {
        return new UserEndpoints(userService());
    }

    @Bean
    public UserAdapterRepository userAdapterRepository() {
        return new UserAdapterRepository(modelMapper(), userDao);
    }

    @Bean
    public GeneratorJWT generatorJWT() {
        return new GeneratorJWT();
    }

    @Bean
    public UserService userService() {
        return new UserService(userAdapterRepository(), generatorJWT());
    }
}
