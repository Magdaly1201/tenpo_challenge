package com.magdy.challenge.tenpo.core.percentage.port;

import org.springframework.cache.annotation.Cacheable;

import java.io.IOException;
import java.util.Optional;

public interface PercentageClient {

    @Cacheable(value = "PERCENTAGE")
    Optional<Integer> getPercentage();

}
