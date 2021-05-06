package com.dzavorin.solutions.amazon;

public class RobotBoundedInCircle {

    int[] directions = new int[] {0, 1, 0, -1, 0};

    public boolean isRobotBounded(String instructions) {

        int[] position = new int[]{0, 0};
        int direction = 0;
        for (int i = 0; i < instructions.length(); i++) {
            char ch = instructions.charAt(i);

            if (ch == 'L') {
                direction--;
            } else if (ch == 'R') {
                direction++;
            } else {
                int d = Math.abs(direction % 4);
                position[0] += directions[d];
                position[1] += directions[d + 1];
            }

        }

        return (position[0] == 0 && position[1] == 0) || Math.abs(direction % 4) != 0;
    }

}
