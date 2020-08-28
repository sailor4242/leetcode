package com.dzavorin.solutions.math;

public class ImplementRand10UsingRand7 {
    public int rand7() {
        return (int) Math.random() % 7; // correct ???
    }

    public int rand10() {

        int x  = -1;
        for(int i = 0; i < 10; i++) {
            x += rand7();
        }
        // x is [0...69]
        return x % 10 + 1;

    }
}
