package com.magdy.challenge.tenpo.adapter.gateway;

import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

public class PercentageClientImpl implements PercentageClient {

    private final RestTemplate restTemplate;
    private final int MIN = 1;
    private final int MAX = 100;
    private final int COUNT = 0;

    public PercentageClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Integer> getPercentage() {
        Integer[] percentage = restTemplate.getForObject("http://www.randomnumberapi.com/api/v1.0/random?min=" + MIN + "&max=" + MAX + "&count=" + COUNT + "", Integer[].class);
        return Arrays.stream(percentage).findFirst();
    }
}
