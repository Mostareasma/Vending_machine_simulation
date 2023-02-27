package com.example.enums;

public enum Coin {
    COIN_1_POINT_5(1.5),
    COIN_1(1),
    COIN_2(2),
    COIN_5(5),
    COIN_10(10);

    private double value;

    private Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}