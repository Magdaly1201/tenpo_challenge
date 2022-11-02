package com.magdy.challenge.tenpo.core.sum.model;

public class Sum {

    private final int value1;
    private final int value2;
    private int sum;
    private float percentageSum;
    private float percentageValue;
    private float total;

    public Sum(int value1, int value2, float percentageValue) {
        this.value1 = value1;
        this.value2 = value2;
        this.sum();
        this.percentageValue(percentageValue);
        this.percentageSum();
        this.total();
    }

    public void sum(){
        this.sum = value1+value2;
    }

    public void percentageValue(float percentageValue){
        this.percentageValue = percentageValue;
    }

    public void percentageSum(){
        this.percentageSum = (percentageValue * sum) / 100;
    }

    public void total(){
        this.total = sum + percentageSum;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getSum() {
        return sum;
    }

    public float getPercentageSum() {
        return percentageSum;
    }

    public float getTotal() {
        return total;
    }

    public float getPercentageValue() {
        return percentageValue;
    }
}
