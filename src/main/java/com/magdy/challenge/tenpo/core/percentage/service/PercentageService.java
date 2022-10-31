package com.magdy.challenge.tenpo.core.percentage.service;

import com.magdy.challenge.tenpo.core.history.service.HistoryService;
import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "PERCENTAGE")
    public float obtainPercentage(){
        System.out.println("RETRY: method percentage client called "+attempts++);
        Integer percentage = percentageClient.getPercentage().orElseThrow(() -> new RuntimeException("ERROR: not value for percentage"));
        historyService.createTransaction("percentage","magdaly",percentage.toString(),"OK");
        System.out.println("item service called");
        return percentage;
    }

    @Recover
    public float errorFallback(Exception e){
        attempts = 0;
        System.out.println("ERROR: service percentage client is down retry: "+attempts);
        historyService.createTransaction("percentage","magdaly","error message","ERROR");
        return historyService.getLastPercentage();
    }
}
