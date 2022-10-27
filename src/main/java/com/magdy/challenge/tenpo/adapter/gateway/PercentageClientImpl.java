package com.magdy.challenge.tenpo.adapter.gateway;

import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class PercentageClientImpl implements PercentageClient {

    private final RestTemplate restTemplate;

    public PercentageClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Integer> getPercentage() throws IOException {
        Integer[] percentage = restTemplate.getForObject("http://www.randomnumberapi.com/api/v1.0/random?min=1&max=100&count=1", Integer[].class);

        return Arrays.stream(percentage).findFirst();
    }
}
