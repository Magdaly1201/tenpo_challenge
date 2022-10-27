package com.magdy.challenge.tenpo.core.percentage.service;

import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;

import java.io.IOException;

public class PercentageService {

    private final PercentageClient percentageClient;

    public PercentageService(PercentageClient percentageClient) {
        this.percentageClient = percentageClient;
    }

    public Integer percentage(int value){
        try {
            return percentageClient.getPercentage().orElseThrow(()-> new RuntimeException("ERROR: not value for percentage"));
        } catch (IOException e) {
           throw new RuntimeException("ERROR: not value for percentage");
        }
    }
}
