package com.magdy.challenge.tenpo.adapter.gateway;

import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

public class PercentageClientImpl implements PercentageClient {

    private final RestTemplate restTemplate;
    private final String randomIntegerHost;

    public PercentageClientImpl(RestTemplate restTemplate, String randomIntegerHost) {
        this.restTemplate = restTemplate;
        this.randomIntegerHost = randomIntegerHost;
    }

    @Override
    public Optional<Integer> getPercentage() {
        Integer[] percentage = restTemplate.getForObject(randomIntegerHost, Integer[].class);
        return Arrays.stream(percentage).findFirst();
    }
}
