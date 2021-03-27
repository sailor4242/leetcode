package com.dzavorin.solutions.dfs;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        if (num > 10 || num < 0) return res;

        dfs(num, 0, 0, new int[10], res);
        return res;
    }

    private void dfs(int num, int j, int led, int[] leds, List<String> res) {
        if (led == num) {
            String time = buildTime(leds);
            if (time != null) res.add(time);
            return;
        }

        if (led > 10) {
            return;
        }


        for (int i = j; i < 10; i++) {
            leds[i] = 1;
            dfs(num, i + 1,led + 1, leds, res);
            leds[i] = 0;
        }
    }

    private String buildTime(int[] leds) {

        int minute = 0;
        int hour = 0;
        for (int i = 0; i < 4; i++) {
            if (leds[i] == 1)  hour += Math.pow(2, 3 - i);
        }

        for (int i = 9; i >= 4; i--) {
            if (leds[i] == 1)  minute += Math.pow(2, 9 - i);
        }

        if (hour >= 12 || minute >= 60) return null;

        String min = minute / 10 > 0 ? String.valueOf(minute) : "0" + minute;
        return hour + ":" + min;
    }

    /// Counting bits (1) will do the trick, for getting all the numbers that match the target num for the binary clock.

    public List<String> readBinaryWatch2(int num) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (hDist(i) + hDist(j) == num) {
                    result.add(String.format("%d:%02d", i, j));
                }
            }
        }

        return result;
    }

    // Count bits with Hamming Distance
    public int hDist(int num) {
        int ans = 0;

        while (num != 0) {
            ans++;
            num = num & (num - 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new BinaryWatch().readBinaryWatch(2));
    }
}
