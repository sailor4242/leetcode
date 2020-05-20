package com.dzavorin.solutions.stack;

import java.util.Stack;

public class OnlineStockSpan {

    Stack<int[]> stack = new Stack<>();

    OnlineStockSpan() {}

    public int next(int price) {
        int res = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            res += stack.pop()[1];
        }
        stack.push(new int[]{price, res});
        return res;
    }

}
