package com.magdy.challenge.tenpo.infrastructure.app.config;

import com.magdy.challenge.tenpo.core.history.service.HistoryService;
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

    public static final String PERCENTAGE = "PERCENTAGE";
    public static final String HISTORY_PERCENTAGE ="HISTORY_PERCENTAGE";
    public  HistoryService historyService;

    public CacheConfig(HistoryService historyServicet) {
        this.historyService = historyService;
    }

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(PERCENTAGE,HISTORY_PERCENTAGE);
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {PERCENTAGE,HISTORY_PERCENTAGE})
    @Scheduled(fixedDelay = 30 * 60 * 1000, initialDelay = 60 * 1000)
    public void reportCacheEvict() {
        System.out.println("Flush Cache " + LocalDate.now());
    }
}
