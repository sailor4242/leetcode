package com.dzavorin.solutions.math;

public class RobotBoundedInCircle {

    public boolean isRobotBounded(String instructions) {

        char ch;
        int direction = 1;
        int[] coords = {0, 0};

        for (int i = 0; i < instructions.length(); i++) {
            ch = instructions.charAt(i);
            if (ch == 'L') {
                direction = direction + 1 > 4 ? 1 : direction + 1;
            } else if (ch == 'R') {
                direction = direction - 1 < 1 ? 4 : direction - 1;
            } else {
                switch (direction) {
                    case 1:
                        coords[1] += 1;
                        break;
                    case 2:
                        coords[0] += 1;
                        break;
                    case 3:
                        coords[1] -= 1;
                        break;
                    default:
                        coords[0] -= 1;
                }
            }
        }

        return (coords[0] == 0 && coords[1] == 0) || direction != 1;
    }

}
