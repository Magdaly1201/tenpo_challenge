package com.magdy.challenge.tenpo.core.sum;

import com.magdy.challenge.tenpo.core.percentage.service.PercentageService;

public class SumService {

    public final PercentageService percentageService;

    public SumService(PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    public float operationSumValuesAndPercentage(int value1, int value2) {
        float sum = sum(value1, value2);
        float percentage = (percentageService.obtainPercentage() * sum) / 100;
        return sum(sum, percentage);
    }

    private float sum(float value1, float value2) {
        return value1 + value2;
    }
}
