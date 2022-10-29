package com.magdy.challenge.tenpo.core.percentage.service;

import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;

import java.io.IOException;

public class PercentageService {

    private final PercentageClient percentageClient;

    public PercentageService(PercentageClient percentageClient) {
        this.percentageClient = percentageClient;
    }

    public float percentage(float value){
        Integer percentage = percentageClient.getPercentage().orElseThrow(() -> new RuntimeException("ERROR: not value for percentage"));
        return(percentage * value) / 100;
    }
}
