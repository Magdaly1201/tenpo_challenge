package com.magdy.challenge.tenpo.infrastructure.app.config;

import com.magdy.challenge.tenpo.core.history.model.Status;
import com.magdy.challenge.tenpo.core.history.model.TypeTransaction;
import com.magdy.challenge.tenpo.core.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String PERCENTAGE = "PERCENTAGE";
    public static final String HISTORY_PERCENTAGE = "HISTORY_PERCENTAGE";

    @Autowired
    public MessageService messageService;

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(PERCENTAGE, HISTORY_PERCENTAGE);
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {PERCENTAGE, HISTORY_PERCENTAGE})
    @Scheduled(fixedDelay = 30 * 60 * 100000, initialDelay = 60 * 100000)
    public void reportCacheEvict() {
        messageService.createMessage(TypeTransaction.FLUS_CACHE,"cron",null, Status.OK);
        logger.info("Flush Cache " + LocalDate.now());
    }
}
