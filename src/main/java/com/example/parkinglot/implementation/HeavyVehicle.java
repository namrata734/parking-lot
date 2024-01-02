package com.example.parkinglot.implementation;

public class HeavyVehicle implements Price{
    @Override
    public int lessThanTwoHrs() {
        return 20;
    }

    @Override
    public int moreThanTwoHrs() {
        return 100;
    }

    @Override
    public int moreThanTwelveHrs() {
        return 1000;
    }

    @Override
    public int moreThanTwentyFourGrs() {
        return 2000;
    }
}
