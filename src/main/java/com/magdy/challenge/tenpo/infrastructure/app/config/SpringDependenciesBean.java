package com.magdy.challenge.tenpo.infrastructure.app.config;

import com.magdy.challenge.tenpo.adapter.delivery.SumEndpoints;
import com.magdy.challenge.tenpo.adapter.gateway.PercentageClientImpl;
import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;
import com.magdy.challenge.tenpo.core.sum.SumService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
public class SpringDependenciesBean {

    @Value("${random.integer.host}")
    private String randomIntegerHost;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HistoryService historyService(){
        return new HistoryService();
    }

    @Bean
    public PercentageClientImpl percentageClient(){
        return new PercentageClientImpl(restTemplate(), randomIntegerHost);
    }

    @Bean
    public PercentageService percentageService(){
        return new PercentageService(percentageClient(), historyService());
    }

    @Bean
    public SumService sumService(){
        return new SumService(percentageService());
    }

    @Bean
    public SumEndpoints sumEndpoints() {
        return new SumEndpoints(sumService());
    }

}
