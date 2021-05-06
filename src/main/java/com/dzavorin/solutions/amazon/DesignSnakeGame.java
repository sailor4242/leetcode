package com.dzavorin.solutions.amazon;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.LinkedHashSet;
import java.util.Map;

public class DesignSnakeGame {

    int[][] food;
    int foodIdx = 0;
    CachedLinkedHashSet<Pair<Integer, Integer>> snake;
    int width;
    int height;

    Map<String, int[]> directions = Map.of(
            "L", new int[]{0, -1},
            "R", new int[]{0, 1},
            "U", new int[]{-1, 0},
            "D", new int[]{1, 0}
    );

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public DesignSnakeGame(int width, int height, int[][] food) {
        if (food.length == 0) throw new IllegalArgumentException();

        this.food = food;
        this.snake = new CachedLinkedHashSet<>();
        this.snake.add(new Pair<>(0, 0));

        this.height = height;
        this.width = width;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        Pair<Integer, Integer> snakeHead = snake.getLast();
        int[] dir = directions.get(direction);
        if (dir == null) return -1;
        Pair<Integer, Integer> next = new Pair<>(snakeHead.getKey() + dir[0], snakeHead.getValue() + dir[1]);
        if (next.getKey() < 0 || next.getKey() >= height || next.getValue() < 0 || next.getValue() >= width) return -1;
        Pair<Integer, Integer> snakeTail = snake.iterator().next();
        if (snake.contains(next) && !snakeTail.equals(next)) return -1;

        if (foodIdx < food.length && food[foodIdx][0] == next.getKey() && food[foodIdx][1] == next.getValue()) {
            foodIdx++;
        } else {
            snake.remove(snakeTail);
        }

        snake.add(next);
        return snake.size() - 1;
    }

    static class CachedLinkedHashSet<E> extends LinkedHashSet<E> {
        private E last = null;

        @Override
        public boolean add(E e) {
            last = e;
            return super.add(e);
        }
        public E getLast() {
            return last;
        }

    }

    public static void main(String[] args) {
        var sg = new DesignSnakeGame(3,2, new int[][]{{1,2}, {0,1}});
        System.out.println(sg.move("R"));
        System.out.println(sg.move("D"));
        System.out.println(sg.move("R"));
        System.out.println(sg.move("U"));
        System.out.println(sg.move("L"));
        System.out.println(sg.move("U"));
    }

}
