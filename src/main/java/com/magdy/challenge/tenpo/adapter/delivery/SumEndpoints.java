package com.magdy.challenge.tenpo.adapter.delivery;

import com.magdy.challenge.tenpo.core.percentage.port.PercentageClient;
import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;

import java.io.IOException;

public class SumEndpoints {

    private final PercentageService percentageService;

    public SumEndpoints(PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    public float sum(int value1 , int value2){

        int sum = value1 + value2;
        Integer percentage = percentageService.percentage(sum);
        return (percentage * sum) / 100;
    }

}
