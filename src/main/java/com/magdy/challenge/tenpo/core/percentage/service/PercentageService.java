package com.magdy.challenge.tenpo.core.percentage.service;

import com.magdy.challenge.tenpo.core.history.model.Status;
import com.magdy.challenge.tenpo.core.history.model.TypeTransaction;
import com.magdy.challenge.tenpo.core.message.service.MessageService;
import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import static com.magdy.challenge.tenpo.infrastructure.app.config.CacheConfig.PERCENTAGE;

public class PercentageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PercentageClient percentageClient;
    private final com.magdy.challenge.tenpo.core.history.service.HistoryService historyService;
    private final MessageService messageService;

    private int attempts;

    public PercentageService(PercentageClient percentageClient, com.magdy.challenge.tenpo.core.history.service.HistoryService historyService, MessageService messageService) {
        this.percentageClient = percentageClient;
        this.messageService = messageService;
        this.historyService = historyService;
    }

    @Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @Cacheable(value = PERCENTAGE)
    public float obtainPercentage(Long userId) {
        logger.info("RETRY: method percentage client called " + attempts++);
        Integer percentage = percentageClient.getPercentage().orElseThrow(() -> new RuntimeException("ERROR: not value for percentage"));
        messageService.createMessage(TypeTransaction.PERCENTAGE, userId, percentage.toString(), Status.OK);
        logger.info("service called");
        return percentage;
    }

    @Recover
    public float errorFallback(Exception e) {
        attempts = 0;
        logger.error("ERROR: service percentage client is down retry: " + attempts);
        messageService.createMessage(TypeTransaction.PERCENTAGE, null, "service percentage client is down retry:" + attempts, Status.ERROR);
        return historyService.getLastPercentage();
    }
}
