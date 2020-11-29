package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathInZigZagLabeledBinaryTree {

    public List<Integer> pathInZigZagTree(int label) {
        if (label <= 0) return List.of();
        if (label == 1) return List.of(1);


        LinkedList<Integer> res = new LinkedList<>();

        int lvl = 1;

        while (Math.pow(2, lvl) <= label) {
            lvl++;
        }

        stepUp(label, lvl, res);
        return res;
    }

    public void stepUp(int label, int lvl, LinkedList<Integer> res) {
        res.addFirst(label);

        if (label == 1) return;

        int next = label % 2 == 0 ? label / 2 : (label - 1) / 2;
        int lo = (int) Math.pow(2, lvl - 2);
        int hi = (int) Math.pow(2, lvl - 1) - 1;
        next = lo + (hi - next);

        stepUp(next, lvl - 1, res);
    }


    ///////

    public List<Integer> pathInZigZagTree2(int label) {
        List<Integer> result = new ArrayList<>();
        result.add(label);
        int depth = (int) (Math.log(label) / Math.log(2));
        while (depth > 0) {
            int pl = (int) Math.pow(2, depth - 1);
            int pr = (int) (Math.pow(2, depth) - 1);
            label = pl + pr - label / 2;
            result.add(0, label);
            depth--;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PathInZigZagLabeledBinaryTree().pathInZigZagTree(14)); // 1,3,4,14
        System.out.println(new PathInZigZagLabeledBinaryTree().pathInZigZagTree2(26)); // 1,2,6,10,26
    }
}
