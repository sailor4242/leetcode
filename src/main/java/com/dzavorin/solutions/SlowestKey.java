package com.dzavorin.solutions;

public class SlowestKey {

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int maxtime = releaseTimes[0];
        int keyIdx = 0;
        for (int i = 1; i < releaseTimes.length; i++) {
            int pressTime = releaseTimes[i] - releaseTimes[i - 1];
            if (pressTime > maxtime) {
                maxtime = pressTime;
                keyIdx = i;
            } else if (pressTime == maxtime) {
                keyIdx = keysPressed.charAt(keyIdx) < keysPressed.charAt(i) ? i : keyIdx;
            }
        }

        return keysPressed.charAt(keyIdx);
    }

}
