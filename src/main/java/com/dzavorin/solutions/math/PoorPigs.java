package com.dzavorin.solutions.math;

public class PoorPigs {

    // What if you only have one shot? Eg. 4 buckets, 15 mins to die, and 15 mins to test.
    // How many states can we generate with x pigs and T tests?
    // Find minimum x such that (T+1)^x >= N
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int T = minutesToTest / minutesToDie;
        int min = 0;
        while (Math.pow(T + 1, min) < buckets) {
            min++;
        }

        return min;
    }

}
