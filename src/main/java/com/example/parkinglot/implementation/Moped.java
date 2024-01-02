package com.example.parkinglot.implementation;

public class Moped implements Price{
    @Override
    public int lessThanTwoHrs() {
        return 10;
    }

    @Override
    public int moreThanTwoHrs() {
        return 50;
    }

    @Override
    public int moreThanTwelveHrs() {
        return 500;
    }

    @Override
    public int moreThanTwentyFourGrs() {
        return 1000;
    }
}
