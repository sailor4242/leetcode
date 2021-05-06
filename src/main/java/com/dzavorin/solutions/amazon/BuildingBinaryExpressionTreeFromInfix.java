package com.dzavorin.solutions.amazon;

import java.util.ArrayDeque;
import java.util.Deque;

public class BuildingBinaryExpressionTreeFromInfix {

    static class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public Node expTree(String s) {
        if (s.isEmpty()) return null;
        Deque<Node> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                int j = i + 1;
                for (int bal = 1; j < s.length(); j++) {
                    if (s.charAt(j) == '(') bal++;
                    else if (s.charAt(j) == ')') bal--;
                    if (bal == 0) break;
                }
                Node tmp = expTree(s.substring(i + 1, j));
                if (tmp != null) deque.add(tmp);
                i = j;
            } else {
                deque.add(new Node(s.charAt(i)));
            }
        }
        return helper(helper(deque, '*', '/'), '+', '-').poll();
    }

    private Deque<Node> helper(Deque<Node> deque, char op1, char op2) {
        if (deque.isEmpty()) return null;
        Deque<Node> tmp = new ArrayDeque<>();
        tmp.offer(deque.poll());
        while (!deque.isEmpty()) {
            Node oper = deque.poll();
            if (oper.left == null && (oper.val == op1 || oper.val == op2)) {
                oper.left = tmp.pollLast();
                oper.right = deque.poll();
            }
            tmp.offer(oper);
        }
        return tmp;
    }

}
