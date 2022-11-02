package com.magdy.challenge.tenpo.infrastructure.delivery.dto;

public class SumResponseDTO {

    private int value1;
    private int value2;
    private int sum;
    private float percentageSum;
    private float percentageValue;
    private float total;

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public float getPercentageSum() {
        return percentageSum;
    }

    public void setPercentageSum(float percentageSum) {
        this.percentageSum = percentageSum;
    }

    public float getPercentageValue() {
        return percentageValue;
    }

    public void setPercentageValue(float percentageValue) {
        this.percentageValue = percentageValue;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
