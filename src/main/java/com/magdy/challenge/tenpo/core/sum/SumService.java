package com.magdy.challenge.tenpo.core.sum;

import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;

public class SumService {

    public final PercentageService percentageService;

    public SumService(PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    public float sum(int value1 ,int value2){
        int sum = value1 + value2;
        float percentage = percentageService.percentage(sum);
        return sum + percentage;
    }
}
