package com.magdy.challenge.tenpo.infrastructure.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.magdy.challenge.tenpo")
@EnableJpaRepositories(basePackages = "com.magdy.challenge.tenpo.infrastructure")
@EntityScan("com.magdy.challenge.tenpo.infrastructure.repository")
public class TenpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenpoApplication.class, args);
	}

}
