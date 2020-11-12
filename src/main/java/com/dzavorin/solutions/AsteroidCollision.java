package com.dzavorin.solutions;

import java.util.*;

public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {

        LinkedList<Integer> list = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < asteroids.length; i++) {

            if (list.isEmpty()) {
                if (asteroids[i] < 0) {
                    res.add(asteroids[i]);
                } else {
                    list.add(asteroids[i]);
                }
            } else {
                int last = list.getLast();
                if (asteroids[i] < 0) {
                    if (-1 * asteroids[i] > last) {

                        while (!list.isEmpty() && list.getLast() < -1 * asteroids[i]) {
                            list.removeLast();
                        }

                        if (list.isEmpty()) {
                            res.add(asteroids[i]);
                        } else if (list.getLast() == -1 * asteroids[i]) {
                            list.removeLast();
                        }

                    } else if (asteroids[i] == -1 * last) {
                        list.removeLast();
                    }
                } else {
                    list.add(asteroids[i]);
                }
            }

        }

        while (!list.isEmpty()) {
            res.add(list.poll());
        }

        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    public int[] asteroidCollision2(int[] asteroids) {
        if (asteroids.length <= 1) return asteroids;  // Handling Corner cases

        Stack<Integer> stack = new Stack<>();
        for (int asteriod : asteroids) {
            if (asteriod > 0) {
                stack.push(asteriod);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(stack.peek()) < Math.abs(asteriod)) {
                    stack.pop();
                }

                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteriod);
                } else if (stack.peek() == Math.abs(asteriod)) {
                    stack.pop();
                }
            }
        }

        int[] output = new int[stack.size()];
        for (int i = output.length - 1; i >= 0; i--)
            output[i] = stack.pop();

        return output;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AsteroidCollision().asteroidCollision(new int[]{5, 10, -5})));
    }

}
