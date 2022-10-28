package com.magdy.challenge.tenpo.infrastructure.app.config;

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

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(PERCENTAGE);
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {PERCENTAGE})
    @Scheduled(fixedDelay = 30 * 60 * 1000, initialDelay = 60 * 1000)

    public void reportCacheEvict() {
        System.out.println("Flush Cache " + LocalDate.now());
    }
}
