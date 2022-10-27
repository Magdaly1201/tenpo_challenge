package com.magdy.challenge.tenpo.infrastructure.app.config;

import com.magdy.challenge.tenpo.adapter.delivery.SumEndpoints;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class SpringDependenciesBean {

    @Bean
    public SumEndpoints sumEndpoints() {
        return new SumEndpoints();
    }

}
