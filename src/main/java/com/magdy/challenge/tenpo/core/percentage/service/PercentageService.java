package com.magdy.challenge.tenpo.core.percentage.service;

import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public class PercentageService {

    private final PercentageClient percentageClient;
    private final HistoryService historyService;

    private int attempts;
    public PercentageService(PercentageClient percentageClient, HistoryService historyService) {
        this.percentageClient = percentageClient;
        this.historyService = historyService;
    }

    @Retryable(value = RuntimeException.class, maxAttempts = 3,backoff = @Backoff(delay = 1000))
    public float percentage(float value){
        System.out.println("method called "+attempts++);
        Integer percentage = percentageClient.getPercentage().orElseThrow(() -> new RuntimeException("ERROR: not value for percentage"));
        System.out.println("item service called");

        return(percentage * value) / 100;
    }

    @Recover
    public float errorFallback(Exception e){
        attempts = 0;
        System.out.println("ERROR: service is down");
        return historyService.getLastPercentage();
    }
}
