package com.dzavorin.solutions.math;

public class AngleBetweenHandsOfClock {

    public double angleClock(int hour, int minutes) {

        double hoursSc = (hour == 12 ? 0 : hour) * 60d / 12 + (double) minutes/ 12;
        double hoursPi = hoursSc / 30d;
        double minutesPi = minutes / 30d;
        double res = Math.max(hoursPi - minutesPi, minutesPi - hoursPi) * 180;
        return Math.min(res, 360 - res);
    }

    public static void main(String[] args) {
        System.out.println(new AngleBetweenHandsOfClock().angleClock(12, 30));
        System.out.println(new AngleBetweenHandsOfClock().angleClock(1, 57));
    }
}
